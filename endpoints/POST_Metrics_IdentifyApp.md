# POST Metrics/IdentifyApp

Retrieve identifiers for the specified device, environment and application. The data returned will be used on subsequent calls to the API.

## Request

**DeviceName** (String)  
The name of the device.  
Example: "PROD-RS-Debian-7"

**AppName** (String)  
The name of the application.  
Example: "stackify-agent"

**AppLocation** (String)  
*Optional.* The full directory path for the application.  
Example: "/usr/local/stackify/stackify-agent"

**ConfiguredAppName** (String)  
*Optional.* The name of the application. This overrides the AppName field.  
Example: "my-stackify-agent"

**ConfiguredEnvironmentName** (String)  
*Optional.* The environment name. If the device is monitored by Stackify, we will use the environment associated to the device. If the device is not monitored by Stackify, this should be specified.  
Example: "Prod"

## Response

**DeviceID** (Integer)  
*Optional.* The Stackify device identifier. This will be set if Stackify is monitoring the device.  
Example: 15

**DeviceAppID** (Integer)  
*Optional.* The Stackify device/application identifier. This will be set if Stackify is monitoring the device.  
Example: 751

**AppNameID** (String)  
The Stackify application identifier.  
Example: "82c5d03b-4e0f-4288-89a0-b9524e3efe4c"

**EnvID** (Integer)  
The Stackify environment identifier.  
Example: 1

**Env** (String)  
The environment name.  
Example: "Prod"

**AppName** (String)  
The application name.  
Example: "stackify-agent"

**AppEnvID** (String)  
The Stackify application/environment identifier.  
Example: "f2308146-0101-4933-9716-daf99ea2066a"

**DeviceAlias** (String)  
*Optional.* The device alias. This will be set if Stackify is monitoring the device.  
Example: "PROD-RS-Debian-7"

## Example (Device Monitored by Stackify)

Request:

```
POST https://api.stackify.com/Metrics/IdentifyApp
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
  "DeviceName": "PROD-RS-Debian-7",
  "AppName": "stackify-agent"
}
```

Response:

```javascript
{
  "DeviceID": 15,
  "DeviceAppID": 751,
  "AppNameID": "82c5d03b-4e0f-4288-89a0-b9524e3efe4c",
  "EnvID": 1,
  "Env": "Prod",
  "AppName": "stackify-agent",
  "AppEnvID": "f2308146-0101-4933-9716-daf99ea2066a",
  "DeviceAlias": "PROD-RS-Debian-7"
}
```

## Example (Device Not Monitored by Stackify)

Request:

```
POST https://api.stackify.com/Metrics/IdentifyApp
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
  "DeviceName": "Not-Monitored-RS-Debian-7",
  "AppName": "stackify-agent",
  "ConfiguredEnvironmentName": "Dev"
}
```

Response:

```javascript
{
  "DeviceID": null,
  "DeviceAppID": null,
  "AppNameID": "82c5d03b-4e0f-4288-89a0-b9524e3efe4c",
  "EnvID": 2,
  "Env": "Dev",
  "AppName": "stackify-agent",
  "AppEnvID": "d58b0ed5-cf86-4817-9aa1-06ebb9f6bfdb",
  "DeviceAlias": null
}
```
