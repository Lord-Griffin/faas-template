package me.mith.openfaas.function;

import com.openfaas.model.AbstractHandler;
import com.openfaas.model.IResponse;
import com.openfaas.model.IRequest;
import com.openfaas.model.Response;

public class Handler extends AbstractHandler {

    @Override
    public IResponse Handle(IRequest req) {
        Response res = new Response();
	    res.setBody("Hello, " + req.getBody() + "!");

	    return res;
    }
}
