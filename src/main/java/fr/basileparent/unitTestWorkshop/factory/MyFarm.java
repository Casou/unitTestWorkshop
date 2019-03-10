package fr.basileparent.unitTestWorkshop.factory;

import fr.basileparent.unitTestWorkshop.model.Pony;
import org.springframework.stereotype.Service;

@Service
public class MyFarm {

    public Pony getNewPony() {
        return new Pony();
    }

}
