# POST Log/Save

Send log messages and exceptions to Stackify.

## Example (Log Messages)

```
POST https://api.stackify.com/Log/Save
```

HTTP Headers:
```
Content-Type: application/json
Accept: application/json
X-Stackify-PV: V1
X-Stackify-Key: [STACKIFY_API_KEY]
```

POST Body:
```javascript
{
  "Env": "Prod",
  "ServerName": "PROD-RS-Debian-7",
  "AppName": "stackify-agent",
  "AppLoc": "/usr/local/stackify/stackify-agent",
  "Logger": "stackify-log-log4j12-1.0.12",
  "Platform": "java",
  "Msgs": [
    {
      "Msg": "Example debug message",
      "Th": "main",
      "EpochMs": 1417535434194,
      "Level": "debug",
      "SrcMethod": "com.stackify.error.test.StackifyErrorAppenderTest.main",
      "SrcLine": 57
    },
    {
      "Msg": "Example info message",
      "Th": "main",
      "EpochMs": 1417535434215,
      "Level": "info",
      "SrcMethod": "com.stackify.error.test.SomeClass.someMethod",
      "SrcLine": 19
    }
  ]
}
```

Response:

```javascript
{
  "success": true,
  "took": 31
}
```

## Example (Log Message and Exception)

```
POST https://api.stackify.com/Log/Save
```

HTTP Headers:
```
Content-Type: application/json
Accept: application/json
X-Stackify-PV: V1
X-Stackify-Key: [STACKIFY_API_KEY]
```

POST Body:
```javascript
{
  "Env": "Prod",
  "ServerName": "PROD-RS-Debian-7",
  "AppName": "stackify-agent",
  "AppLoc": "/usr/local/stackify/stackify-agent",
  "Logger": "stackify-log-log4j12-1.0.12",
  "Platform": "java",
  "Msgs": [
    {
      "Msg": "Example error message with exception details",
      "Ex": {
        "EnvironmentDetail": {
          "DeviceName": "eric-centos",
          "AppLocation": "/usr/local/stackify/stackify-agent",
          "ConfiguredAppName": "stackify-agent",
          "ConfiguredEnvironmentName": "Prod"
        },
        "OccurredEpochMillis": 1417537904651,
        "Error": {
          "Message": "java.lang.NullPointerException: Something important was null that can't be null (Example error message with exception details)",
          "ErrorType": "java.lang.RuntimeException",
          "SourceMethod": "com.stackify.error.test.SomeSweetClass.doSomething",
          "StackTrace": [
            {
              "CodeFileName": "SomeSweetClass.java",
              "LineNum": 16,
              "Method": "com.stackify.error.test.SomeSweetClass.doSomething"
            },
            {
              "CodeFileName": "StackifyErrorAppenderTest.java",
              "LineNum": 44,
              "Method": "com.stackify.error.test.StackifyErrorAppenderTest.main"
            }
          ],
          "InnerError": {
            "Message": "Something important was null that can't be null",
            "ErrorType": "java.lang.NullPointerException",
            "SourceMethod": "com.stackify.error.test.SomeFragileClass.throwNullPointerException",
            "StackTrace": [
              {
                "CodeFileName": "SomeFragileClass.java",
                "LineNum": 15,
                "Method": "com.stackify.error.test.SomeFragileClass.throwNullPointerException"
              },
              {
                "CodeFileName": "SomeSweetClass.java",
                "LineNum": 14,
                "Method": "com.stackify.error.test.SomeSweetClass.doSomething"
              },
              {
                "CodeFileName": "StackifyErrorAppenderTest.java",
                "LineNum": 44,
                "Method": "com.stackify.error.test.StackifyErrorAppenderTest.main"
              }
            ]
          }
        },
        "ServerVariables": {
          "java.runtime.name": "OpenJDK Runtime Environment",
          "os.name": "Linux",
          "user.timezone": "UTC",
          "java.vendor": "Oracle Corporation"
        }
      },
      "Th": "main",
      "EpochMs": 1417537904651,
      "Level": "error",
      "SrcMethod": "com.stackify.error.test.StackifyErrorAppenderTest.main",
      "SrcLine": 46
    }
  ]
}
```

Response:

```javascript
{
  "success": true,
  "took": 641
}
```



## Request

**Env** (String)  
The environment name.  
Example: "Prod"

**ServerName** (String)  
The name of the device.  
Example: "PROD-RS-Debian-7"

**AppName** (String)  
The name of the application.  
Example: "stackify-agent"

**AppLocation** (String)  
*Optional.* The full directory path for the application.  
Example: "/usr/local/stackify/stackify-agent"

**Logger** (String)  
The name and version of the logging project generating this request.  
Example: "stackify-log-log4j12-1.0.12"

**Platform** (String)  
The logging language.  
Example: "java"



**Msgs** (List(Object))  
Lists of log message objects.  

> **Msgs[\*]/Msg** (String)  
> The log message.  
> Example: "Example debug message"

> **Msgs[\*]/Level** (String)  
> The log level.  
> Example: "debug"

> **Msgs[\*]/EpochMs** (Long)  
> Unix/POSIX/Epoch time with millisecond precision.  
> Example: 1417535434194

> **Msgs[\*]/Th** (String)  
> *Optional.* The thread name.  
> Example: "main"

> **Msgs[\*]/SrcMethod** (String)  
> *Optional.* Fully qualified method name.  
> Example: "com.stackify.error.test.StackifyErrorAppenderTest.main"

> **Msgs[\*]/SrcLine** (Integer)  
> *Optional.* Line number.  
> Example: 57

> **Msgs[\*]/TransID** (String)  
> *Optional.* Transaction identifier.  
> Example: "c06570b2-ba9d-40be-8078-1394cf331159"

> **Msgs[\*]/Data** (String)  
> *Optional.* Additional JSON metadata about the log event. Special characters need to be escaped.  
> Example: "{\"Key1\":\"Value1\",\"Key2\":\"Value2\"}"

> **Msgs[\*]/Ex** (Object)  
> *Optional.* Exception details.

> > **Msgs[\*]/Ex/EnvironmentDetail** (Object)  
> > Device, application and environment details.

> > > **Msgs[\*]/Ex/EnvironmentDetail/DeviceName** (String)  
> > > The name of the device.  
> > > Example: "PROD-RS-Debian-7"

> > > **Msgs[\*]/Ex/EnvironmentDetail/AppName** (String)  
> > > The name of the application.  
> > > Example: "stackify-agent"

> > > **Msgs[\*]/Ex/EnvironmentDetail/AppLocation** (String)  
> > > *Optional.* The full directory path for the application.  
> > > Example: "/usr/local/stackify/stackify-agent"

> > > **Msgs[\*]/Ex/EnvironmentDetail/ConfiguredAppName** (String)  
> > > *Optional.* The name of the application. This overrides the AppName field.  
> > > Example: "my-stackify-agent"

> > > **Msgs[\*]/Ex/EnvironmentDetail/ConfiguredEnvironmentName** (String)  
> > > *Optional.* The environment name. If the device is monitored by Stackify, we will use the environment associated to the device. If the device is not monitored by Stackify, this should be specified.  
> > > Example: "Prod"

> > **Msgs[\*]/Ex/OccurredEpochMillis** (Long)  
> > Unix/POSIX/Epoch time with millisecond precision.  
> > Example: 1417535434194

> > **Msgs[\*]/Ex/Error** (Object)  
> > Exception details.

> > > **Msgs[\*]/Ex/Error/Message** (String)  
> > > *Optional.* The exception message.  
> > > Example: "java.lang.NullPointerException: Something important was null that can't be null"

> > > **Msgs[\*]/Ex/Error/ErrorType** (String)  
> > > The exception type.  
> > > Example: "java.lang.RuntimeException"

> > > **Msgs[\*]/Ex/Error/ErrorTypeCode** (String)  
> > > *Optional.* The exception type code.  
> > > Example: 40143

> > > **Msgs[\*]/Ex/Error/Data** (Object)  
> > > *Optional.* Additional data from the loggers diagnostic context.  
> > > Example: {"Key1": "Value1", "Key2":"Value2"}

> > > **Msgs[\*]/Ex/Error/SourceMethod** (String)  
> > > *Optional.* Fully qualified method from the first stack trace frame.  
> > > Example: "com.stackify.error.test.SomeSweetClass.doSomething"

> > > **Msgs[\*]/Ex/Error/StackTrace** (List(Object))  
> > > Lists of stack trace frames.

> > > > **Msgs[\*]/Ex/Error/StackTrace[\*]/Method** (String)  
> > > > Fully qualified method name.  
> > > > Example: "com.stackify.error.test.SomeSweetClass.doSomething"

> > > > **Msgs[\*]/Ex/Error/StackTrace[\*]/CodeFileName** (String)  
> > > > *Optional.* File name.  
> > > > Example: "SomeSweetClass.java"

> > > > **Msgs[\*]/Ex/Error/StackTrace[\*]/LineNum** (String)  
> > > > *Optional.* Line number.  
> > > > Example: 16

> > > **Msgs[\*]/Ex/Error/InnerError** (Object)  
> > > *Optional.* Same as Msgs[\*]/Ex/Error object.

> > > **Msgs[\*]/Ex/Error/WebRequestDetail** (Object)  
> > > *Optional.* Web request details.  

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/UserIPAddress** (String)  
> > > > *Optional.* IP address of the user.  
> > > > Example: "127.0.0.1"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/HttpMethod** (String)  
> > > > *Optional.* HTTP method (GET, POST, PUT, DELETE, etc.).  
> > > > Example: "GET"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/RequestProtocol** (String)  
> > > > *Optional.* HTTP request protocol.  
> > > > Example: "HTTP/1.1"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/RequestUrl** (String)  
> > > > *Optional.* HTTP request URL.  
> > > > Example: "https://api.stackify.com/Metrics/IdentifyApp"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/RequestUrlRoot** (String)  
> > > > *Optional.* Request URI.  
> > > > Example: "/Metrics/IdentifyApp"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/ReferralUrl** (String)  
> > > > *Optional.* Value of the "referer" header.  
> > > > Example: "https://api.stackify.com/Metrics/IdentifyApp"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/Headers** (Object)  
> > > > *Optional.* HTTP header name/value pairs.  
> > > > Example: {"Content-Type": "application/json", "Content-Encoding":"gzip"}

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/Cookies** (Object)  
> > > > *Optional.* HTTP cookie name/value pairs. We mask all cookie values with "X-MASKED-X".  
> > > > Example: {"name": "X-MASKED-X"}

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/QueryString** (Object)  
> > > > *Optional.* HTTP query string name/value pairs.  
> > > > Example: {"name": "value"}

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/PostData** (Object)  
> > > > *Optional.* HTTP post data name/value pairs.  
> > > > Example: {"name": "value"}

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/SessionData** (Object)  
> > > > *Optional.* HTTP session name/value pairs. We mask all session values with "X-MASKED-X".  
> > > > Example: {"name": "X-MASKED-X"}

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/PostDataRaw** (String)  
> > > > *Optional.* HTTP raw post data.  
> > > > Example: "name=value"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/MVCArea** (String)  
> > > > *Optional.* MVC area.  
> > > > Example: "API"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/MVCController** (String)  
> > > > *Optional.* MVC controller.  
> > > > Example: "Metrics"

> > > > **Msgs[\*]/Ex/Error/WebRequestDetail/MVCAction** (String)  
> > > > *Optional.* MVC action.  
> > > > Example: "IdentifyApp"

> > > **Msgs[\*]/Ex/Error/ServerVariables** (Object)  
> > > *Optional.* Server data name/value pairs.  
> > > Example: {"java.runtime.name": "OpenJDK Runtime Environment", "os.name": "Linux", "user.timezone": "UTC", "java.vendor": "Oracle Corporation"}

> > > **Msgs[\*]/Ex/Error/CustomerName** (String)  
> > > *Optional.* Customer/client name.  
> > > Example: "Stackify"

> > > **Msgs[\*]/Ex/Error/UserName** (String)  
> > > *Optional.* User name.  
> > > Example: "test-user@stackify.com

###Optional advanced fields

**CDID** (Integer)  
*Optional.* The Stackify device identifier. This is from the [<code>POST</code> Metrics/IdentifyApp](POST_Metrics_IdentifyApp.md) response.  
Example: 15

**CDAppID** (Integer)  
*Optional.* The Stackify device/application identifier. This is from the [<code>POST</code> Metrics/IdentifyApp](POST_Metrics_IdentifyApp.md) response.  
Example: 751

**AppNameID** (String)  
*Optional.* The Stackify application identifier. This is from the [<code>POST</code> Metrics/IdentifyApp](POST_Metrics_IdentifyApp.md) response.  
Example: "82c5d03b-4e0f-4288-89a0-b9524e3efe4c"

**AppEnvID** (String)  
*Optional.* The Stackify application/environment identifier. This is from the [<code>POST</code> Metrics/IdentifyApp](POST_Metrics_IdentifyApp.md) response.  
Example: "f2308146-0101-4933-9716-daf99ea2066a"

**EnvID** (Integer)  
*Optional.* The Stackify environment identifier. This is from the [<code>POST</code> Metrics/IdentifyApp](POST_Metrics_IdentifyApp.md) response.  
Example: 1

## Response

**success** (Boolean)  
True indicates success, false otherwise.  
Example: true

**took** (Integer)  
Elapsed time in milliseconds.  
Example: 31

