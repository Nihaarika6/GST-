import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Customer } from './customer';
import { Orders } from './orders'
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  // placeOrder(model: import("./orders").Orders) {
  //   throw new Error("Method not implemented.");
  // }

  constructor(private http : Http) { }

  validateCustomer(user : string, pwd : string) : Observable<string> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/customer/"+user+"/"+pwd).
    map((res : Response) => res.text());
  }
  findByCustomerName(user : string) : Observable<Customer> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/customer/"+user).
    map((res : Response) => res.json());
  }
  orderHistory(customerId : number) : Observable<Customer[]> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/"+customerId).
    map((res : Response) => res.json());
  }
  orderPending(customerId : number) : Observable<Customer[]> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/pendingcus/"+customerId).
    map((res : Response) => res.json());
}
// http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/101
// walletshow(customerId : number) : Observable<Wallet[]> {
//   return this.
//   http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/wallet/"+customerId).
//   map((res : Response) => res.json());

// }
placeOrder(orders : Orders) : Observable<string> {
  return this.http.post("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/placeOrder/",orders).
  map((res : Response) => res.text());
}
cancelOrder(orderId : number, customerId : number, status : string) : Observable<string> {
  return this.http.post("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/CancelOrder/"+ orderId+ "/" + customerId + "/" +status, null).
  map((res : Response) => res.text());
  }
}