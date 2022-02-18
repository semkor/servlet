import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.*;
import java.util.*;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = ItemController.findById(Long.parseLong(req.getParameter("itemid")));
        resp.getWriter().println(item);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader br=req.getReader()) {
            String line;
            List<String> list=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line.replaceAll("(,$)","").replaceAll("\"","").split(":")[1]);
            }
            ItemController.save(createObjectSave(list));
        } catch (IOException | ParseException e) {
            System.err.println("Reading from URL failed");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedReader br=req.getReader()) {
            String line;
            List<String> list=new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line.replaceAll("(,$)","").replaceAll("\"","").split(":")[1]);
            }
            ItemController.update(createObjectUpdate(list));
        } catch (IOException | ParseException e) {
            System.err.println("Reading from URL failed");
        }
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemController.delete(Long.parseLong(req.getParameter("itemid")));
    }


    private static Item createObjectSave(List<String> list) throws ParseException {
        Item item=new Item();
            item.setName(list.get(0));
            if(!list.get(1).equals("null"))
                item.setDateCreated(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(1)));
            if(!list.get(2).equals("null"))
                item.setLastUpdatedDate(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(2)));
            item.setDescription(list.get(3));
        System.out.println();
        return item;
    }

    private static Item createObjectUpdate(List<String> list) throws ParseException {
        Item item=new Item();
            item.setId(Long.parseLong(list.get(0)));
            item.setName(list.get(1));
            if(!list.get(2).equals("null"))
                item.setDateCreated(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(2)));
            if(!list.get(3).equals("null"))
                item.setLastUpdatedDate(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(3)));
            item.setDescription(list.get(4));
        return item;
    }
}
