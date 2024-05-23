import org.example.domain.PathPlanningResult;
import org.example.domain.Plane;
import org.example.service.PathPlanningService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.logging.Logger;

public class PathPlanningServiceUnitTest {
    private final PathPlanningService pathPlanningService = new PathPlanningService();
    private final static Logger LOGGER = Logger.getLogger(PathPlanningServiceUnitTest.class.getName());

    @Test
    public void shouldReturn16() throws IOException {
        PathPlanningResult result = pathPlanningService.calculateMostValuablePath(20, 8, 20L,
                new Plane(0, new int[]{1,1}));
        LOGGER.info("Total value: " + result.getTotalValue());
        assert (result.getTotalValue() == 16);
    }

    @Test
    public void shouldReturn364() throws IOException {
        PathPlanningResult result = pathPlanningService.calculateMostValuablePath(100, 40, 100L,
                new Plane(0, new int[]{1,1}));
        LOGGER.info("Total value: " + result.getTotalValue());
        assert (result.getTotalValue() == 364);
    }

    @Test
    public void shouldReturn29909() throws IOException {
        PathPlanningResult result = pathPlanningService.calculateMostValuablePath(1000, 400, 1000L,
                new Plane(0, new int[]{1,1}));
        LOGGER.info("Total value: " + result.getTotalValue());
        assert (result.getTotalValue() == 29909);
    }
}
