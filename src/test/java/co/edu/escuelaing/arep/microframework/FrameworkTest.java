package co.edu.escuelaing.arep.microframework;

import org.junit.Test;
import static org.junit.Assert.*;

public class FrameworkTest {

    @Test
    public void testRequestQueryStringParsing() {
        Request req = new Request("/test", "name=Pedro&age=30");
        assertEquals("Pedro", req.getValues("name"));
        assertEquals("30", req.getValues("age"));
        assertNull(req.getValues("nonexistent"));
    }

    @Test
    public void testEmptyQueryString() {
        Request req = new Request("/test", "");
        assertNull(req.getValues("name"));
    }

    @Test
    public void testMicroSpringRouteRegistration() {
        MicroSpring.get("/testroute", (req, resp) -> "route works");
        Route route = MicroSpring.getRoute("/testroute");
        assertNotNull("La ruta debe estar registrada", route);

        Request mockReq = new Request("/testroute", "");
        Response mockResp = new Response();
        assertEquals("route works", route.handle(mockReq, mockResp));
    }

    @Test
    public void testMicroSpringStaticFilesLocation() {
        MicroSpring.staticfiles("testfolder");
        assertEquals("testfolder", MicroSpring.getStaticFilesLocation());
    }
}
