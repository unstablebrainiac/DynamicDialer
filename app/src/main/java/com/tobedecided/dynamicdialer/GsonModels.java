package com.tobedecided.dynamicdialer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sajalnarang on 8/1/17.
 */

public class GsonModels {
    public class Response {
        @SerializedName("Results")
        private Results results;

        public Response(Results results) {
            this.results = results;
        }

        public Results getResults() {
            return results;
        }

        public void setResults(Results results) {
            this.results = results;
        }
    }

    public class Output1 {
        @SerializedName("type")
        private String type;
        @SerializedName("value")
        private Value value;

        public Output1(String type, Value value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }

    public class Results {
        @SerializedName("output1")
        private Output1 output1;

        public Results(Output1 output1) {
            this.output1 = output1;
        }

        public Output1 getOutput1() {
            return output1;
        }

        public void setOutput1(Output1 output1) {
            this.output1 = output1;
        }
    }

    public class Value {
        @SerializedName("ColumnNames")
        private List<String> columnNames = null;
        @SerializedName("ColumnTypes")
        private List<String> columnTypes = null;
        @SerializedName("Values")
        private List<List<String>> values = null;

        public Value(List<String> columnNames, List<String> columnTypes, List<List<String>> values) {
            this.columnNames = columnNames;
            this.columnTypes = columnTypes;
            this.values = values;
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public void setColumnNames(List<String> columnNames) {
            this.columnNames = columnNames;
        }

        public List<String> getColumnTypes() {
            return columnTypes;
        }

        public void setColumnTypes(List<String> columnTypes) {
            this.columnTypes = columnTypes;
        }

        public List<List<String>> getValues() {
            return values;
        }

        public void setValues(List<List<String>> values) {
            this.values = values;
        }
    }
}
