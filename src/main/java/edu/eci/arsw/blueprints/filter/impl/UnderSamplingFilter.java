package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UnderSamplingFilter implements Filter {
    @Override
    public Blueprint filterPoints(Blueprint blueprint) {
        blueprint.setPoints(suppressInterleated(blueprint.getPoints()));
        return blueprint;
    }

    private List<Point> suppressInterleated(List<Point> points) {
        List<Point> suppresedPointsList = new ArrayList<Point>();
        suppresedPointsList.add(points.get(0));
        Boolean suppress = false;
        for (int i = 1; i < points.size(); i++) {
            if (suppress){
                suppresedPointsList.add(points.get(i));
                suppress = false;
            }else {
                suppress= true;
            }
        }
        return suppresedPointsList;
    }
}
