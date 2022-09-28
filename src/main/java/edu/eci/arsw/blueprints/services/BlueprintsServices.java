/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filter.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {

    private final BlueprintsPersistence bpp;

    private final Filter filter;

    @Autowired
    public BlueprintsServices(BlueprintsPersistence bpp, Filter filter) {
        this.bpp = bpp;
        this.filter = filter;
    }

    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author,name);
    }

    public Blueprint filterBlueprint(Blueprint blueprint){
        return filter.filterPoints(blueprint);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        try {
            Set<Blueprint> blueprints = bpp.getBlueprintsByAuthor(author);
            return blueprints;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new BlueprintNotFoundException("Author not found");
        }
    }

    public void editBlueprint(String author, String name, Blueprint blueprint) throws BlueprintNotFoundException {
        try {
            System.out.println(author);
            System.out.println(name);
            System.out.println(blueprint);
            bpp.editBlueprint(author,name,blueprint);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new BlueprintNotFoundException("Author not found");
        }
    }
}
