package com.tobedecided.dynamicdialer;

import com.google.gson.annotations.Expose;
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

    public class BigMLResponse {
        @SerializedName("boosted_ensemble")
        @Expose
        private Boolean boostedEnsemble;
        @SerializedName("category")
        @Expose
        private Integer category;
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("confidence")
        @Expose
        private Double confidence;
        @SerializedName("configuration")
        @Expose
        private Object configuration;
        @SerializedName("configuration_status")
        @Expose
        private Boolean configurationStatus;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("credits")
        @Expose
        private Double credits;
        @SerializedName("dataset")
        @Expose
        private String dataset;
        @SerializedName("dataset_status")
        @Expose
        private Boolean datasetStatus;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("dev")
        @Expose
        private Boolean dev;
        @SerializedName("fields")
        @Expose
        private Fields fields;
        @SerializedName("importance")
        @Expose
        private Importance importance;
        @SerializedName("input_data")
        @Expose
        private InputData inputData;
        @SerializedName("locale")
        @Expose
        private String locale;
        @SerializedName("missing_strategy")
        @Expose
        private Integer missingStrategy;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("model_status")
        @Expose
        private Boolean modelStatus;
        @SerializedName("model_type")
        @Expose
        private Integer modelType;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("number_of_models")
        @Expose
        private Integer numberOfModels;
        @SerializedName("objective_field")
        @Expose
        private String objectiveField;
        @SerializedName("objective_field_name")
        @Expose
        private String objectiveFieldName;
        @SerializedName("objective_fields")
        @Expose
        private List<String> objectiveFields = null;
        @SerializedName("output")
        @Expose
        private String output;
        @SerializedName("prediction")
        @Expose
        private Prediction prediction;
        @SerializedName("prediction_path")
        @Expose
        private PredictionPath predictionPath;
        @SerializedName("private")
        @Expose
        private Boolean _private;
        @SerializedName("probabilities")
        @Expose
        private List<List<String>> probabilities = null;
        @SerializedName("probability")
        @Expose
        private Double probability;
        @SerializedName("project")
        @Expose
        private Object project;
        @SerializedName("query_string")
        @Expose
        private String queryString;
        @SerializedName("resource")
        @Expose
        private String resource;
        @SerializedName("shared")
        @Expose
        private Boolean shared;
        @SerializedName("source")
        @Expose
        private String source;
        @SerializedName("source_status")
        @Expose
        private Boolean sourceStatus;
        @SerializedName("status")
        @Expose
        private Status status;
        @SerializedName("subscription")
        @Expose
        private Boolean subscription;
        @SerializedName("tags")
        @Expose
        private List<Object> tags = null;
        @SerializedName("task")
        @Expose
        private String task;
        @SerializedName("tlp")
        @Expose
        private Integer tlp;
        @SerializedName("updated")
        @Expose
        private String updated;

        public BigMLResponse(Boolean boostedEnsemble, Integer category, Integer code, Double confidence, Object configuration, Boolean configurationStatus, String created, Double credits, String dataset, Boolean datasetStatus, String description, Boolean dev, Fields fields, Importance importance, InputData inputData, String locale, Integer missingStrategy, String model, Boolean modelStatus, Integer modelType, String name, Integer numberOfModels, String objectiveField, String objectiveFieldName, List<String> objectiveFields, String output, Prediction prediction, PredictionPath predictionPath, Boolean _private, List<List<String>> probabilities, Double probability, Object project, String queryString, String resource, Boolean shared, String source, Boolean sourceStatus, Status status, Boolean subscription, List<Object> tags, String task, Integer tlp, String updated) {
            this.boostedEnsemble = boostedEnsemble;
            this.category = category;
            this.code = code;
            this.confidence = confidence;
            this.configuration = configuration;
            this.configurationStatus = configurationStatus;
            this.created = created;
            this.credits = credits;
            this.dataset = dataset;
            this.datasetStatus = datasetStatus;
            this.description = description;
            this.dev = dev;
            this.fields = fields;
            this.importance = importance;
            this.inputData = inputData;
            this.locale = locale;
            this.missingStrategy = missingStrategy;
            this.model = model;
            this.modelStatus = modelStatus;
            this.modelType = modelType;
            this.name = name;
            this.numberOfModels = numberOfModels;
            this.objectiveField = objectiveField;
            this.objectiveFieldName = objectiveFieldName;
            this.objectiveFields = objectiveFields;
            this.output = output;
            this.prediction = prediction;
            this.predictionPath = predictionPath;
            this._private = _private;
            this.probabilities = probabilities;
            this.probability = probability;
            this.project = project;
            this.queryString = queryString;
            this.resource = resource;
            this.shared = shared;
            this.source = source;
            this.sourceStatus = sourceStatus;
            this.status = status;
            this.subscription = subscription;
            this.tags = tags;
            this.task = task;
            this.tlp = tlp;
            this.updated = updated;
        }

        public Boolean getBoostedEnsemble() {
            return boostedEnsemble;
        }

        public void setBoostedEnsemble(Boolean boostedEnsemble) {
            this.boostedEnsemble = boostedEnsemble;
        }

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }

        public Object getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Object configuration) {
            this.configuration = configuration;
        }

        public Boolean getConfigurationStatus() {
            return configurationStatus;
        }

        public void setConfigurationStatus(Boolean configurationStatus) {
            this.configurationStatus = configurationStatus;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public Double getCredits() {
            return credits;
        }

        public void setCredits(Double credits) {
            this.credits = credits;
        }

        public String getDataset() {
            return dataset;
        }

        public void setDataset(String dataset) {
            this.dataset = dataset;
        }

        public Boolean getDatasetStatus() {
            return datasetStatus;
        }

        public void setDatasetStatus(Boolean datasetStatus) {
            this.datasetStatus = datasetStatus;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getDev() {
            return dev;
        }

        public void setDev(Boolean dev) {
            this.dev = dev;
        }

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }

        public Importance getImportance() {
            return importance;
        }

        public void setImportance(Importance importance) {
            this.importance = importance;
        }

        public InputData getInputData() {
            return inputData;
        }

        public void setInputData(InputData inputData) {
            this.inputData = inputData;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public Integer getMissingStrategy() {
            return missingStrategy;
        }

        public void setMissingStrategy(Integer missingStrategy) {
            this.missingStrategy = missingStrategy;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Boolean getModelStatus() {
            return modelStatus;
        }

        public void setModelStatus(Boolean modelStatus) {
            this.modelStatus = modelStatus;
        }

        public Integer getModelType() {
            return modelType;
        }

        public void setModelType(Integer modelType) {
            this.modelType = modelType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getNumberOfModels() {
            return numberOfModels;
        }

        public void setNumberOfModels(Integer numberOfModels) {
            this.numberOfModels = numberOfModels;
        }

        public String getObjectiveField() {
            return objectiveField;
        }

        public void setObjectiveField(String objectiveField) {
            this.objectiveField = objectiveField;
        }

        public String getObjectiveFieldName() {
            return objectiveFieldName;
        }

        public void setObjectiveFieldName(String objectiveFieldName) {
            this.objectiveFieldName = objectiveFieldName;
        }

        public List<String> getObjectiveFields() {
            return objectiveFields;
        }

        public void setObjectiveFields(List<String> objectiveFields) {
            this.objectiveFields = objectiveFields;
        }

        public String getOutput() {
            return output;
        }

        public void setOutput(String output) {
            this.output = output;
        }

        public Prediction getPrediction() {
            return prediction;
        }

        public void setPrediction(Prediction prediction) {
            this.prediction = prediction;
        }

        public PredictionPath getPredictionPath() {
            return predictionPath;
        }

        public void setPredictionPath(PredictionPath predictionPath) {
            this.predictionPath = predictionPath;
        }

        public Boolean get_private() {
            return _private;
        }

        public void set_private(Boolean _private) {
            this._private = _private;
        }

        public List<List<String>> getProbabilities() {
            return probabilities;
        }

        public void setProbabilities(List<List<String>> probabilities) {
            this.probabilities = probabilities;
        }

        public Double getProbability() {
            return probability;
        }

        public void setProbability(Double probability) {
            this.probability = probability;
        }

        public Object getProject() {
            return project;
        }

        public void setProject(Object project) {
            this.project = project;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public Boolean getShared() {
            return shared;
        }

        public void setShared(Boolean shared) {
            this.shared = shared;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Boolean getSourceStatus() {
            return sourceStatus;
        }

        public void setSourceStatus(Boolean sourceStatus) {
            this.sourceStatus = sourceStatus;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Boolean getSubscription() {
            return subscription;
        }

        public void setSubscription(Boolean subscription) {
            this.subscription = subscription;
        }

        public List<Object> getTags() {
            return tags;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        public Integer getTlp() {
            return tlp;
        }

        public void setTlp(Integer tlp) {
            this.tlp = tlp;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    public class Fields {
        @SerializedName("000000")
        @Expose
        private Name _000000;
        @SerializedName("000001")
        @Expose
        private Weekday _000001;
        @SerializedName("000004")
        @Expose
        private Hour _000004;

        public Fields(Name _000000, Weekday _000001, Hour _000004) {
            this._000000 = _000000;
            this._000001 = _000001;
            this._000004 = _000004;
        }

        public Name get_000000() {
            return _000000;
        }

        public void set_000000(Name _000000) {
            this._000000 = _000000;
        }

        public Weekday get_000001() {
            return _000001;
        }

        public void set_000001(Weekday _000001) {
            this._000001 = _000001;
        }

        public Hour get_000004() {
            return _000004;
        }

        public void set_000004(Hour _000004) {
            this._000004 = _000004;
        }
    }

    public class Importance {
        @SerializedName("000001")
        @Expose
        private Double _000001;
        @SerializedName("000004")
        @Expose
        private Double _000004;

        public Importance(Double _000001, Double _000004) {
            this._000001 = _000001;
            this._000004 = _000004;
        }

        public Double get_000001() {
            return _000001;
        }

        public void set_000001(Double _000001) {
            this._000001 = _000001;
        }

        public Double get_000004() {
            return _000004;
        }

        public void set_000004(Double _000004) {
            this._000004 = _000004;
        }
    }

    public class InputData {
        @SerializedName("000001")
        @Expose
        private String _000001;
        @SerializedName("000004")
        @Expose
        private String _000004;

        public InputData(String _000001, String _000004) {
            this._000001 = _000001;
            this._000004 = _000004;
        }

        public String get_000001() {
            return _000001;
        }

        public void set_000001(String _000001) {
            this._000001 = _000001;
        }

        public String get_000004() {
            return _000004;
        }

        public void set_000004(String _000004) {
            this._000004 = _000004;
        }
    }

    public class ObjectiveSummary {
        @SerializedName("categories")
        @Expose
        private List<List<String>> categories = null;

        public ObjectiveSummary(List<List<String>> categories) {
            this.categories = categories;
        }

        public List<List<String>> getCategories() {
            return categories;
        }

        public void setCategories(List<List<String>> categories) {
            this.categories = categories;
        }
    }

    public class Path {
        @SerializedName("field")
        @Expose
        private String field;
        @SerializedName("operator")
        @Expose
        private String operator;
        @SerializedName("value")
        @Expose
        private Integer value;

        public Path(String field, String operator, Integer value) {
            this.field = field;
            this.operator = operator;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public class Prediction {
        @SerializedName("000000")
        @Expose
        private String _000000;

        public Prediction(String _000000) {
            this._000000 = _000000;
        }

        public String get_000000() {
            return _000000;
        }

        public void set_000000(String _000000) {
            this._000000 = _000000;
        }
    }

    public class PredictionPath {
        @SerializedName("bad_fields")
        @Expose
        private List<Object> badFields = null;
        @SerializedName("confidence")
        @Expose
        private Double confidence;
        @SerializedName("next_predicates")
        @Expose
        private List<Object> nextPredicates = null;
        @SerializedName("objective_summary")
        @Expose
        private ObjectiveSummary objectiveSummary;
        @SerializedName("path")
        @Expose
        private List<Path> path = null;
        @SerializedName("unknown_fields")
        @Expose
        private List<Object> unknownFields = null;

        public PredictionPath(List<Object> badFields, Double confidence, List<Object> nextPredicates, ObjectiveSummary objectiveSummary, List<Path> path, List<Object> unknownFields) {
            this.badFields = badFields;
            this.confidence = confidence;
            this.nextPredicates = nextPredicates;
            this.objectiveSummary = objectiveSummary;
            this.path = path;
            this.unknownFields = unknownFields;
        }

        public List<Object> getBadFields() {
            return badFields;
        }

        public void setBadFields(List<Object> badFields) {
            this.badFields = badFields;
        }

        public Double getConfidence() {
            return confidence;
        }

        public void setConfidence(Double confidence) {
            this.confidence = confidence;
        }

        public List<Object> getNextPredicates() {
            return nextPredicates;
        }

        public void setNextPredicates(List<Object> nextPredicates) {
            this.nextPredicates = nextPredicates;
        }

        public ObjectiveSummary getObjectiveSummary() {
            return objectiveSummary;
        }

        public void setObjectiveSummary(ObjectiveSummary objectiveSummary) {
            this.objectiveSummary = objectiveSummary;
        }

        public List<Path> getPath() {
            return path;
        }

        public void setPath(List<Path> path) {
            this.path = path;
        }

        public List<Object> getUnknownFields() {
            return unknownFields;
        }

        public void setUnknownFields(List<Object> unknownFields) {
            this.unknownFields = unknownFields;
        }
    }

    public class Status {
        @SerializedName("bad_fields")
        @Expose
        private List<Object> badFields = null;
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("elapsed")
        @Expose
        private Double elapsed;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("progress")
        @Expose
        private Integer progress;
        @SerializedName("unknown_fields")
        @Expose
        private List<Object> unknownFields = null;

        public Status(List<Object> badFields, Integer code, Double elapsed, String message, Integer progress, List<Object> unknownFields) {
            this.badFields = badFields;
            this.code = code;
            this.elapsed = elapsed;
            this.message = message;
            this.progress = progress;
            this.unknownFields = unknownFields;
        }

        public List<Object> getBadFields() {
            return badFields;
        }

        public void setBadFields(List<Object> badFields) {
            this.badFields = badFields;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public Double getElapsed() {
            return elapsed;
        }

        public void setElapsed(Double elapsed) {
            this.elapsed = elapsed;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getProgress() {
            return progress;
        }

        public void setProgress(Integer progress) {
            this.progress = progress;
        }

        public List<Object> getUnknownFields() {
            return unknownFields;
        }

        public void setUnknownFields(List<Object> unknownFields) {
            this.unknownFields = unknownFields;
        }
    }

    public class TermAnalysis {
        @SerializedName("enabled")
        @Expose
        private Boolean enabled;

        public TermAnalysis(Boolean enabled) {
            this.enabled = enabled;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    public class Name {
        @SerializedName("column_number")
        @Expose
        private Integer columnNumber;
        @SerializedName("datatype")
        @Expose
        private String datatype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("optype")
        @Expose
        private String optype;
        @SerializedName("order")
        @Expose
        private Integer order;
        @SerializedName("preferred")
        @Expose
        private Boolean preferred;
        @SerializedName("term_analysis")
        @Expose
        private TermAnalysis termAnalysis;

        public Name(Integer columnNumber, String datatype, String name, String optype, Integer order, Boolean preferred, TermAnalysis termAnalysis) {
            this.columnNumber = columnNumber;
            this.datatype = datatype;
            this.name = name;
            this.optype = optype;
            this.order = order;
            this.preferred = preferred;
            this.termAnalysis = termAnalysis;
        }

        public Integer getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOptype() {
            return optype;
        }

        public void setOptype(String optype) {
            this.optype = optype;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Boolean getPreferred() {
            return preferred;
        }

        public void setPreferred(Boolean preferred) {
            this.preferred = preferred;
        }

        public TermAnalysis getTermAnalysis() {
            return termAnalysis;
        }

        public void setTermAnalysis(TermAnalysis termAnalysis) {
            this.termAnalysis = termAnalysis;
        }
    }

    public class Weekday {
        @SerializedName("column_number")
        @Expose
        private Integer columnNumber;
        @SerializedName("datatype")
        @Expose
        private String datatype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("optype")
        @Expose
        private String optype;
        @SerializedName("order")
        @Expose
        private Integer order;
        @SerializedName("preferred")
        @Expose
        private Boolean preferred;

        public Weekday(Integer columnNumber, String datatype, String name, String optype, Integer order, Boolean preferred) {
            this.columnNumber = columnNumber;
            this.datatype = datatype;
            this.name = name;
            this.optype = optype;
            this.order = order;
            this.preferred = preferred;
        }

        public Integer getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOptype() {
            return optype;
        }

        public void setOptype(String optype) {
            this.optype = optype;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Boolean getPreferred() {
            return preferred;
        }

        public void setPreferred(Boolean preferred) {
            this.preferred = preferred;
        }
    }

    public class Hour {
        @SerializedName("column_number")
        @Expose
        private Integer columnNumber;
        @SerializedName("datatype")
        @Expose
        private String datatype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("optype")
        @Expose
        private String optype;
        @SerializedName("order")
        @Expose
        private Integer order;
        @SerializedName("preferred")
        @Expose
        private Boolean preferred;

        public Hour(Integer columnNumber, String datatype, String name, String optype, Integer order, Boolean preferred) {
            this.columnNumber = columnNumber;
            this.datatype = datatype;
            this.name = name;
            this.optype = optype;
            this.order = order;
            this.preferred = preferred;
        }

        public Integer getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOptype() {
            return optype;
        }

        public void setOptype(String optype) {
            this.optype = optype;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Boolean getPreferred() {
            return preferred;
        }

        public void setPreferred(Boolean preferred) {
            this.preferred = preferred;
        }
    }
}
