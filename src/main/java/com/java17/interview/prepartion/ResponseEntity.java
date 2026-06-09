public class ResponseEntity {
    
}
/**
 * 
 * 
 * Yes, you can combine ApiResponse<PaymentDto> with ResponseEntity and HttpHeaders.

Using Constructor
@PostMapping
public ResponseEntity<ApiResponse<PaymentDto>> createPayment(
        @RequestBody PaymentRequest request) {

    PaymentDto payment = paymentService.create(request);

    ApiResponse<PaymentDto> response =
            new ApiResponse<>(
                    true,
                    "Payment created successfully",
                    payment
            );

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Request-Id", UUID.randomUUID().toString());
    headers.add("X-Service", "Payment-Service");

    return new ResponseEntity<>(
            response,
            headers,
            HttpStatus.CREATED
    );
}
Using Builder Pattern (Preferred)
@PostMapping
public ResponseEntity<ApiResponse<PaymentDto>> createPayment(
        @RequestBody PaymentRequest request) {

    PaymentDto payment = paymentService.create(request);

    ApiResponse<PaymentDto> response =
            new ApiResponse<>(
                    true,
                    "Payment created successfully",
                    payment
            );

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("X-Request-Id", UUID.randomUUID().toString())
            .header("X-Service", "Payment-Service")
            .body(response);
}
Response

Headers

HTTP/1.1 201 Created
X-Request-Id: 8f2f9b4a
X-Service: Payment-Service
Content-Type: application/json

Body

{
  "success": true,
  "message": "Payment created successfully",
  "data": {
    "paymentId": 101,
    "orderId": "ORD123",
    "amount": 5000,
    "status": "SUCCESS"
  }
}
Constructor Signatures
new ResponseEntity<>(body, status);
new ResponseEntity<>(headers, status);
new ResponseEntity<>(body, headers, status);

Examples:

ResponseEntity<ApiResponse<PaymentDto>>
ResponseEntity<List<PaymentDto>>
ResponseEntity<Page<PaymentDto>>
ResponseEntity<Void>
ResponseEntity<byte[]>

The most common enterprise pattern is:

return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);

and only add HttpHeaders when you need metadata such as:

Request ID
Correlation ID
Pagination information
Rate limit information
Resource Location (Location header for 201 Created)


 */