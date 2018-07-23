package behaviours;

import models.Visitor;

public interface ISecurity {

    public boolean isAllowed(Visitor visitor);

}
