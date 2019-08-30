package com.d3lph1.bridge.common.network;

import java.util.ArrayList;
import java.util.List;

public class MappingRegistry
{
    private List<Mapping> mappings = new ArrayList<>();

    public <T> void add(Mapping<? extends T> mapping)
    {
        mappings.add(mapping);
    }

    public boolean has(int packetId)
    {
        for (Mapping mapping : mappings) {
            if (mapping.getPacketId() == packetId) {
                return true;
            }
        }

        return false;
    }

    public Mapping get(int packetId)
    {
        for (Mapping mapping : mappings) {
            if (mapping.getPacketId() == packetId) {
                return mapping;
            }
        }

        return null;
    }

    public Mapping getMappingByPacketClass(Class<? extends Packet> packetClass)
    {
        for (Mapping mapping : mappings) {
            if (mapping.getPacketClass().getName().equals(packetClass.getName())) {
                return mapping;
            }
        }

        return null;
    }
}
