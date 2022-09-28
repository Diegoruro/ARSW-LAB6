/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 *
 * @author hcadavid
 */
@Component
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        Point[] pts1=new Point[]{new Point(100, 100),new Point(135, 145)};
        Blueprint bp1=new Blueprint("Diego", "prueba",pts1);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        Point[] pts2=new Point[]{new Point(130, 120),new Point(195, 115)};
        Blueprint bp2=new Blueprint("Diego", "prueba2",pts2);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        Point[] pts3=new Point[]{new Point(170, 134),new Point(120, 193)};
        Blueprint bp3=new Blueprint("Juan", "prueba3",pts3);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> blueprints1 = new HashSet<>();
        for (Blueprint singleBlueprint:blueprints.values()) {
            if (Objects.equals(singleBlueprint.getAuthor(), author)){

                blueprints1.add(blueprints.get(new Tuple<>(author,singleBlueprint.getName())));
            }
        }
        return blueprints1;
    }

    @Override
    public Set<Blueprint> getBlueprints() {
        return new HashSet<>(blueprints.values());
    }

    @Override
    public void editBlueprint(String author, String name, Blueprint blueprint) throws Exception {
        try {
            if (blueprints.get(new Tuple<>(author, name))==null){
                throw new Exception("Author or blueprint does not exist");
            }
            blueprints.put(new Tuple<>(author, name),blueprint);
        }catch (Exception ex){
            throw new Exception("not found",ex);
        }
    }


}
