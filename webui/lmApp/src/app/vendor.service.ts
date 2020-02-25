import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';
import { Vendor } from './vendor';
// import { Orders} from './orders';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  showVendor(): Observable<Vendor[]> {
    // throw new Error("Method not implemented.");
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/vendor/").
    map((res : Response)=> res.json());
  }

  constructor(private http : Http) { }

  validateVendor(user : string, pwd : string) : Observable<string> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/vendor/"+user+"/"+pwd).
    map((res : Response) => res.text());
  }
  findByVendorName(user : string) : Observable<Vendor> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/vendor/"+user).
    map((res : Response) => res.json());
  }
  orderHistory(vendorId : number) : Observable<Vendor[]> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/Vendor/"+vendorId).
    map((res : Response) => res.json());
  }
  pendingVendorOrders(vendorId : number) : Observable<Vendor[]> {
    return this.
    http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/pendingven/"+vendorId).
    map((res : Response) => res.json());
  }
  acceptOrReject(orderId : number, vendorId : number, status : String) : Observable<string> {
    return this.http.post("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/Orders/acceptOrRejectOrder/"+orderId + "/" + vendorId + "/" +status,null).
    map((res : Response) => res.text());
    }
    searchVendor(vendorName : number) : Observable<Vendor>{
      return this.
      http.get(" http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/vendor/"+vendorName).
      map((res : Response)=> res.json());
  }
}