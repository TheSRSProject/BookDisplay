package snw.srs.bookdisplay;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public final class PluginResourceBookLoader implements BookLoader {
    private final Plugin plugin;

    @SneakyThrows
    @Override
    public BookData loadBook(String resourceLocation) {
        InputStream stream = plugin.getResource(resourceLocation);
        Preconditions.checkNotNull(stream,
                "Resource '" + resourceLocation + "' not found");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        BookData result = BookJsonDeserializer.deserialize(reader);
        reader.close();
        return result;
    }
}
