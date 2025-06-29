package io.github.lunaprime.dragoninncobblemon.net;

import io.github.lunaprime.dragoninncobblemon.DragonInnCobblemonMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class Packets {
    public record chatMessage(String message) implements CustomPayload{
        public static Identifier ID = Identifier.of(DragonInnCobblemonMod.MOD_ID, "message_packet");
        public static final CustomPayload.Id<chatMessage> PACKET_ID = new CustomPayload.Id<>(ID);
        public static final PacketCodec<RegistryByteBuf, chatMessage> CODEC = PacketCodec.of(chatMessage::write, chatMessage::read);

        @Override
        public Id<? extends CustomPayload> getId() {
            return PACKET_ID;
        }

        public void write(RegistryByteBuf buffer){
            buffer.writeString(message);
        }

        public static chatMessage read(RegistryByteBuf buffer){
            String messageContents = buffer.readString();
            return new chatMessage(messageContents);
        }
    }
}
