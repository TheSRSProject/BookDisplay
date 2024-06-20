package snw.srs.bookdisplay;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PluginResourceBookLoaderTest {
    private ServerMock server;
    private BookDisplay plugin;

    @BeforeEach
    public void setUp()
    {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(BookDisplay.class);
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }

    @Test
    public void loadBook() {
        PluginResourceBookLoader loader = new PluginResourceBookLoader(plugin);
        BookData data = loader.loadBook("test.json");

        assertDoesNotThrow(data::asAdventureBook);
        assertDoesNotThrow(data::asItemStack);
    }
}