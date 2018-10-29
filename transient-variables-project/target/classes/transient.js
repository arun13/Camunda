
//var transient="arun";
//execution.setVariable("transient",transient);
var typedTransientStringValue = Java.type("org.camunda.bpm.engine.variable.value.TypedValue")
var variable =	Java.type("org.camunda.bpm.engine.variable.Variables")
typedTransientStringValue = variable.stringValue("foobar", true);
print(typedTransientStringValue.getValue());
typedTransientStringValue
//execution.setVariable("typed",typedTransientStringValue);


//var transient=Java.type("org.camunda.bpm.engine.variable.value.TypedValue");
////

//var transient="arun";
//execution.setVariable("transient",transient);
var typedTransientStringValue = Java.type("org.camunda.bpm.engine.variable.value.TypedValue")
var variable =	Java.type("org.camunda.bpm.engine.variable.Variables")
typedTransientStringValue = variable.stringValue("foobar", true);
execution.setVariable("typed",typedTransientStringValue);
print(typedTransientStringValue.getValue());

////////
var untransient = "arun";
execution.setVariable("untransient",untransient);
typedTransientStringValue
////

print(transient);
print(execution.getVariable("typed"));
print("untransient");
print(execution.getVariable("untransient"));
