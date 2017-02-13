package org.ag.ants_utils;

import org.ag.utils.FileUtils;

import java.io.IOException;
import java.util.List;

public class Initializer {
    public static void init() {
        // Set the dimension variable
        List<String> configVariables;
        try {
            configVariables = FileUtils.readFileToList("ants.config");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int dimension = Integer.parseInt(configVariables.get(0).split(":")[1]);
        Variables.DIM = dimension;
    }
}
