package steps;

import java.util.logging.Logger;
import com.inter2025api.facedes.ListManagementFacade;

import com.inter2025api.context.TestContext;

public class ListManagementSteps {
    private static final Logger logger = Logger.getLogger(ListManagementSteps.class.getName());
    private final TestContext testContext;
    private final ListManagementFacade listManagementFacade;

    public ListManagementSteps(TestContext testContext, ListManagementFacade listManagementFacade) {
        this.testContext = testContext;
        this.listManagementFacade = listManagementFacade;
    }

}