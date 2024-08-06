import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/payment/process'
        body([
                orderId: 1,
                cardNumber: "card-number",
                amount: 20.0
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body([
                orderId: 1,
                cardNumber: "card-number",
                amount: 20.0
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
