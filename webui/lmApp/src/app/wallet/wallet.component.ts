import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Wallet } from '../wallet';
import { WalletService } from '../wallet.service';
import { Customer } from '../customer';
import { CustomerLoginComponent } from '../customer-login/customer-login.component';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit {
  wallet : Observable<Wallet[]>
  //wallet1 : Observable<Wallet[]>
  customerId:number;
  customer:CustomerLoginComponent;
  sum:number;
  walletId : number;
  show() {
    this.wallet=this.walletService.showByWalletCustomerId(this.customerId);
  }

  constructor(private walletService : WalletService) { 
    this.sum=0;
    // this.wallet=walletService.showWallet();
    this.customer=localStorage.getItem('customer')?JSON.parse(localStorage.getItem('customer')):[];
    this.customerId=this.customer.customerId;
    this.wallet=walletService.showWallet();
    this.wallet=walletService.showByWalletCustomerId(this.customerId);
  }

  
  
  // wallet : Observable<Wallet[]>;
  // // wallet1 : Observable<Wallet[]>
  // customerId:number;
  // customer:Customer;
  
  // // show() {
  // //   // this.wallet1=this.walletService.walletshow(this.walletId);
  // //   this.wallet=this.walletService.walletshow(this.customerId);
  // // }

  // constructor(private walletService : WalletService) { 
  //   //this.wallet=walletService.walletshow(this.walletId);
  //   // this.wallet1=walletService.showByWalletCustomerId(this.walletId);
  //   this.wallet=this.walletService.walletshow(this.customer.customerId);
  // }

  ngOnInit() {
  }

}
// customerName : string;
// customerId : number;
// customer : Customer;
// orders : Observable<Customer[]>;
// orders1:Observable<Customer[]>;
// // wallet:Observable<Wallet[]>;
// constructor(private customerService : CustomerService) {
//   this.customer = localStorage.getItem('customer')? JSON.parse(localStorage.getItem('customer')):[];
//   // alert("Customer Id is " +this.customer.cusId);
//   this.orders=this.customerService.orderHistory(this.customer.customerId);
//    this.orders1=this.customerService.orderPending(this.customer.customerId);
// //  this.wallet=this.customerService.walletshow(this.customer.customerId);