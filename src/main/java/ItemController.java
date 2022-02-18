public class ItemController {

    public static Item save(Item item) {
        return ItemService.save(item);
    }

    public static Item update(Item item) {
        return ItemService.update(item);
    }

    public static Item findById(long id) {
        return ItemService.findById(id);
    }

    public static void delete(long id) {
        ItemService.delete(id);
    }
}
