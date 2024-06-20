package snw.srs.bookdisplay;

import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.io.Reader;

public final class BookJsonDeserializer {
    private BookJsonDeserializer() {
    }

    public static BookData deserialize(Reader jsonReader) {
        return GsonComponentSerializer.gson().serializer()
                .fromJson(jsonReader, BookData.class);
    }
}
