public class ItemService {

    public static Item save(Item item) {
        return ItemDAO.save(item);
    }

    public static Item update(Item item) {
        return ItemDAO.update(item);
    }

    public static Item findById(long id) {
        return ItemDAO.findById(id);
    }

    public static void delete(long id) {
        ItemDAO.delete(id);
    }
}
