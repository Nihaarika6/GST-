import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-customer-pending',
  templateUrl: './customer-pending.component.html',
  styleUrls: ['./customer-pending.component.css']
})
export class CustomerPendingComponent implements OnInit {
  customerName : string;
  customerId : number;
  customer : Customer;
  selValue : number;
  values : string;
  filter : string[];
  feature = 'Feature 1'
  arr : [];
  rs : string;
  status : string;
  selectedFeatures: any = [];
  //orders : Observable<Customer[]>;
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
    //this.orders=this.customerService.orderHistory(this.customer.customerId);
     this.orders1=this.customerService.orderPending(this.customer.customerId);
//  this.wallet=this.customerService.walletshow(this.customer.customerId);
  }
   checkIfAllSelected(x) {
    this.selectedFeatures.push(x);
    // alert("added");
  }
  cancelMe() {
    this.values = this.selectedFeatures.toString();
    // alert(this.values);
    this.filter = this.values.split(",");
    this.status="YES";
    for(var v=0;v<this.filter.length;v++) {
      alert(this.filter[v]);
      this.customerService.cancelOrder(parseInt(this.filter[v]),this.customer.customerId,this.status).subscribe(x => {
        this.rs=x;
      })
    }
    alert("Selected Orders Cancelled Successfully...");
    this._router.navigate(['/dashBoard']);
    // alert(this.customer.customerId);
  }
  ngOnInit() {
  }

}
