/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trixsolucao.mkws.model.mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

/**
 *
 * @author Renan
 */
public class MapToObject {





    public static <T> List<T> mapToObject(List<Map<String, String>> listMap, Class<T> clazz) {

        List<T> retorno = new ArrayList<>();

        List<Element<Field, MkMapping>> elements = getAnnotatedFields(MkMapping.class, clazz);

        for (Map<String, String> map : listMap) {
            retorno.add(getObjectFromMap(map, elements, clazz));
        }

        return retorno;

    }

    public static String getStringFromObject(Object object) {
       return  getStringFromMap(getMapFromObject(object));
    }


    public static Map<String, Object> getMapFromObject(Object object) {
        List<Element<Field, MkMapping>> elements = getAnnotatedFields(MkMapping.class, object.getClass());
        Map<String, Object> map = new HashMap<>();
        try {
            for (Element<Field, MkMapping> e : elements) {
                Field f = e.getField();

                f.setAccessible(true);

                //ignora nulos
                if (f.get(object) == null) {
                    continue;
                }
                String chave = "";
                if (e.getAnnotation().from().trim().equals("id")) {
                    chave = "." + e.getAnnotation().from().trim();
                } else {
                    chave = e.getAnnotation().from().trim();
                }
                // if is primitive, cast as primitive
                if (f.getType().isPrimitive()) {

                    map.put("." + e.getAnnotation().from(), f.get(object));

                } else {
                    //Se for String coloco aspas simples por conta dos espaços
                    if (f.getType().isAssignableFrom(String.class)) {
                        map.put(chave, "'" + f.get(object) + "'");
                    } else {
                        map.put(chave, f.get(object));
                    }

                }
            }

        } catch (IllegalAccessException ex) {
            Logger.getLogger(MapToObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return map;
    }

    public static String getStringFromMap(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {

            Object value = map.get(key);

            if (key != null && value != null) {
                stringBuilder.append(" ");
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value.toString());

            }

        }

        return stringBuilder.toString();
    }

    private static <T> T getObjectFromMap(Map<String, String> map, List<Element<Field, MkMapping>> elementos, Class<T> clazz) {
        try {
            Object object = clazz.newInstance();

            for (Element<Field, MkMapping> e : elementos) {

                Field f = e.getField();

                f.setAccessible(true);

                // if is primitive, cast as primitive
                if (f.getType().isPrimitive()) {

                    if (f.getType().isAssignableFrom(boolean.class
                    )) {
                        f.set(object, Boolean.valueOf((String) map.get(
                                e.getAnnotation().from())));

                    } else if (f.getType().isAssignableFrom(int.class
                    )) {
                        int intValue = Integer.parseInt(map.get(
                                e.getAnnotation().from()).toString());
                        f.set(object, intValue);

                    } else if (f.getType().isAssignableFrom(double.class
                    )) {
                        double doubleValue = Double.parseDouble(map.get(
                                e.getAnnotation().from()).toString());
                        f.set(object, doubleValue);

                    } else if (f.getType().isAssignableFrom(long.class
                    )) {
                        long longValue = Long.parseLong(map.get(
                                e.getAnnotation().from()).toString());
                        f.set(object, longValue);
                    }
                } else {
                    if (f.getType().isAssignableFrom(Boolean.class
                    )) {
                        f.set(object, Boolean.valueOf((String) map.get(
                                e.getAnnotation().from())));

                    } else {
                        // if object, cast normal to the complex object (String, Integer..)
                        f.set(object, f.getType().cast(map.get(e.getAnnotation().from())));
                    }
                }
            }

            return (T) object;

        } catch (InstantiationException ex) {
            Logger.getLogger(MapToObject.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            Logger.getLogger(MapToObject.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static <A extends Annotation> List<Element<Field, A>>
            getAnnotatedFields(Class<A> annotation, Class<?> clazz) {

        List<Element<Field, A>> elementos = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {

            if (f.isAnnotationPresent(annotation)) {
                elementos.add(new Element<>(f, f.getAnnotation(annotation)));
            }
        }

        return elementos;

    }

    public static class Element<F, A extends Annotation> {

        private F field;
        private A annotation;

        /**
         *
         * @param field
         * @param annotation
         */
        public Element(F field, A annotation) {
            this.field = field;
            this.annotation = annotation;
        }

        /**
         * @return the field
         */
        public F getField() {
            return field;
        }

        /**
         * @return the annotation
         */
        public A getAnnotation() {
            return annotation;
        }
    }

}
