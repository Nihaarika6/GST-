import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
import {Router, ActivatedRoute, Route} from '@angular/router';
import { Customer } from '../customer';
// import { Wallet } from '../wallet';

@Component({
  selector: 'app-customer-order-history',
  templateUrl: './customer-order-history.component.html',
  styleUrls: ['./customer-order-history.component.css']
})
export class CustomerOrderHistoryComponent implements OnInit {

  customerName : string;
  customerId : number;
  customer : Customer;
  orders : Observable<Customer[]>;
  orders1:Observable<Customer[]>;
  // wallet:Observable<Wallet[]>;
  cancelOrder(orderId,customerId) {
    localStorage.setItem("cancelOrderId",orderId);
    localStorage.setItem("cancelCustomerId",customerId);
    this._router.navigate(['../customerCancelOrder'],{relativeTo: this._route});
  }
  constructor(private customerService : CustomerService, private _route :ActivatedRoute,private _router: Router) {
    this.customer = localStorage.getItem('customer')? JSON.parse(localStorage.getItem('customer')):[];
    // alert("Customer Id is " +this.customer.cusId);
    this.orders=this.customerService.orderHistory(this.customer.customerId);
     this.orders1=this.customerService.orderPending(this.customer.customerId);
//  this.wallet=this.customerService.walletshow(this.customer.customerId);
   }
  ngOnInit() {
   
  }

}