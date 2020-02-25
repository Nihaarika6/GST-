import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs';
import {Wallet} from './wallet';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class WalletService {

//   constructor(private http :Http) { }
//   walletshow(customerId : number) : Observable<Wallet[]> {
//     return this.
//     http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/wallet/"+customerId).
//     map((res : Response) => res.json());
// }
// }
constructor(private http : Http) { }
showByWalletCustomerId(customerId : number) : Observable<Wallet[]>{
  return this.
  http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/wallet/"+customerId).
  map((res : Response)=> res.json());
}
showWallet() : Observable<Wallet[]>{
  return this.
  http.get("http://localhost:8080/MLP176-0.0.1-SNAPSHOT/api/wallet/").
  map((res : Response)=> res.json());
}
}