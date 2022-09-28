package edu.eci.arsw.blueprints.filter.impl;

import edu.eci.arsw.blueprints.filter.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class RedundancyFilter implements Filter {
    @Override
    public Blueprint filterPoints(Blueprint blueprint) {
        if (blueprint.getPoints().size()>=2){
            blueprint.setPoints(suppressSamePoints(suppressSamePoints(blueprint.getPoints())));
        }
        return blueprint;
    }

    private List<Point> suppressSamePoints(List<Point> points) {
        List<Point> suppresedPointsList = new ArrayList<Point>();
        suppresedPointsList.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            if (!(points.get(i).getX()==points.get(i-1).getX() && points.get(i).getY()==points.get(i-1).getY())){
                suppresedPointsList.add(points.get(i));
            }
        }
        return suppresedPointsList;
    }
}
