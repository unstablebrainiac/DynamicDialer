package com.tobedecided.dynamicdialer;

/**
 * Created by sajalnarang on 26/3/17.
 */

public class Constants {
    private static GsonModels.BigMLPredictionsResponse bigMLPredictionsResponse;

    public static GsonModels.BigMLPredictionsResponse getBigMLPredictionsResponse() {
        return bigMLPredictionsResponse;
    }

    public static void setBigMLPredictionsResponse(GsonModels.BigMLPredictionsResponse bigMLPredictionsResponse) {
        Constants.bigMLPredictionsResponse = bigMLPredictionsResponse;
    }
}
