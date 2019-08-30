package com.d3lph1.bridge.common.network.packets;

import com.d3lph1.bridge.common.network.Packet;

import java.util.Optional;

public class MoveResultPacket implements Packet
{
    private Value value;

    public MoveResultPacket(Value value)
    {
        this.value = value;
    }

    public Value getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s(value=%s)",
                getClass().getSimpleName(),
                getValue().toString()
        );
    }

    public enum Value
    {
        SUCCESS(0),
        INVALID_CARD_SET(1),
        INVALID_CARD_COMBINATION(2);

        private int code;

        Value(int code)
        {
            this.code = code;
        }

        public int getCode()
        {
            return code;
        }

        public static Optional<Value> fromCode(int code)
        {
            for (Value value : Value.values()) {
                if (value.getCode() == code) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }
}
