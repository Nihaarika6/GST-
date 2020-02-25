import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-customer-cancel-order',
  templateUrl: './customer-cancel-order.component.html',
  styleUrls: ['./customer-cancel-order.component.css']
})
export class CustomerCancelOrderComponent implements OnInit {

  customerId : number;
  orderId : number;
  status : string;
  result : string;
  constructor(private _customerService : CustomerService) {
   this.orderId =parseInt(localStorage.getItem("cancelOrderId"));
   this.customerId =parseInt(localStorage.getItem("cancelCustomerId"));
   }

  cancel() {
    this._customerService.cancelOrder(this.orderId,this.customerId,this.status).subscribe(x => {
      this.result=x;
    })
  }

  ngOnInit() {
  }

}
