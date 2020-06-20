package br.com.trixsolucao.mkws.model;

import br.com.trixsolucao.mkws.model.mapping.MkMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resources {

    @MkMapping(from = "uptime")
    private String uptime;

    @MkMapping(from = "version")
    private String version;

    @MkMapping(from = "build-time")
    private String buildTime;

    @MkMapping(from = "factory-software")
    private String factorySoftware;

    @MkMapping(from = "free-memory")
    private String freeMemory;

    @MkMapping(from = "total-memory")
    private String totalMemory;

    @MkMapping(from = "cpu")
    private String cpu;

    @MkMapping(from = "cpu-count")
    private String cpuCount;

    @MkMapping(from = "cpu-frequency")
    private String cpuFrequency;

    @MkMapping(from = "cpu-load")
    private String cpuLoad;

    @MkMapping(from = "free-hdd-space")
    private String freeHdSpace;

    @MkMapping(from = "write-sect-since-reboot")
    private String writeSectSinceReboot;

    @MkMapping(from = "write-sect-total")
    private String writeSectTotal;

    @MkMapping(from = "bad-blocks")
    private String badBlocks;

    @MkMapping(from = "architecture-name")
    private String architectureName;

    @MkMapping(from = "board-name")
    private String boardName;

    @MkMapping(from = "platform")
    private String platform;


}
