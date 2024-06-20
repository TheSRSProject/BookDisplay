package snw.srs.bookdisplay;

import lombok.Getter;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public record BookData(String title, String author, List<List<Component>> pages) {

    public Book asAdventureBook() {
        TextComponent titleComp = Component.text(title);
        TextComponent authorComp = Component.text(author);
        return Book.book(titleComp, authorComp, populatePages());
    }

    public List<Component> populatePages() {
        return pages.stream()
                .map(list -> Component.join(JoinConfiguration.noSeparators(), list))
                .toList();
    }

    public ItemStack asItemStack() {
        ItemStack result = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) result.getItemMeta();
        assert meta != null;
        meta.setTitle(title);
        meta.setAuthor(author);
        for (Component page : populatePages()) {
            String serialized = GsonComponentSerializer.gson().serialize(page);
            meta.addPage(serialized);
        }
        result.setItemMeta(meta);
        return result;
    }

}
