# Exception handling
You can handle an exception by declaring a method annotated @ExceptionHandler like below.

```
@RestController
@RequestMapping("/test")
public class HttpStatusHandlingController {

    ......

    @PostMapping
    public void doStuff(@RequestBody RequestData requestData) {
        System.out.println(requestData.getMessage());
        if (requestData.getMessage().equals("foo")) {
            throw new FooException("Foo 例外");
        };
    }

    ......

    @ResponseBody
    @ExceptionHandler(FooException.class)
    public Map<String, Object> handleFooException(FooException e) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Error foo");
        e.printStackTrace();

        return result;
    }
}
```

 If the controller throws FooException

