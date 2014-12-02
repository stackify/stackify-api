# REST API Best Practices

## Batching

- The Log API was designed to be able to send batches of log messages from the same application in a single HTTP request. We use a batch size of 100 log messages.

## Error Handling

- Use [exponential backoff](http://en.wikipedia.org/wiki/Exponential_backoff) to increase the wait time between calls if HTTP errors occur.

## Transport

- Use HTTP compression. We support gzip.

