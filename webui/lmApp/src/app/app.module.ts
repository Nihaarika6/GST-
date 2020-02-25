import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import {RouterModule, Routes} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { VendorinfoComponent } from './vendorinfo/vendorinfo.component';
import { DashboardComponent } from './dash-board/dash-board.component';
import { VendordashboardComponent } from './vendordashboard/vendordashboard.component';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { PlaceOrderComponent } from './place-order/place-order.component';
import { VendorpendingorderComponent } from './vendorpendingorder/vendorpendingorder.component';
import { VendorloginComponent } from './vendorlogin/vendorlogin.component';
import { WalletComponent } from './wallet/wallet.component';
import { CustomerCancelOrderComponent } from './customer-cancel-order/customer-cancel-order.component';
import { VendoracceptorrejectComponent } from './vendoracceptorreject/vendoracceptorreject.component';
import { IndexComponent } from './index/index.component';
import { VendorComponent } from './vendor/vendor.component';
import { CustomerOrderHistoryComponent } from './customer-order-history/customer-order-history.component';
import { VendororderhistoryComponent } from './vendororderhistory/vendororderhistory.component';
import { LogOutComponent } from './log-out/log-out.component';
import { CustomerPendingComponent } from './customer-pending/customer-pending.component';
import { CustomFilterPipe } from './custom-filter.pipe';
import { AboutComponent } from './about/about.component';
import { QrCodeComponent } from './qr-code/qr-code.component';
import { QRCodeModule } from 'angularx-qrcode';

const appRoutes : Routes = [
  {path : 'dashBoard', component : DashboardComponent,
  children :
  [
    {path:'placeOrder', component:PlaceOrderComponent, outlet:'data'},
    {path:'customerOrderHistory', component:CustomerOrderHistoryComponent, outlet:'data'},
    {path:'wallet', component:WalletComponent, outlet:'data'},
    {path:'customerCancelOrder', component:CustomerCancelOrderComponent, outlet:'data'},
    {path:'customerpendingorder', component:CustomerPendingComponent, outlet:'data'},
    {path:'qrComponent', component:QrCodeComponent, outlet:'data'},
  ]
 },


 {path : 'vendordashboard', component : VendordashboardComponent,
  children :
  [
    {path:'vendor', component:VendorComponent, outlet:'data'},
    {path:'vendorpendingorder', component:VendorpendingorderComponent, outlet:'data'},
    {path:'vendoracceptorreject', component:VendoracceptorrejectComponent, outlet:'data'},
    {path:'vendororderhistory', component:VendororderhistoryComponent, outlet:'data'},
  ]
},


{path : 'index', component : IndexComponent, 
children :
[
  {path:'vendorlogin', component: VendorloginComponent, outlet:'data'},
  {path:'customer-login', component: CustomerLoginComponent, outlet:'data'},
  {path:'logOut', component: LogOutComponent, outlet:'data'},
  {path:'about', component: AboutComponent, outlet:'data'},
]
},
  {path : '', component : IndexComponent },
]
@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    CustomerLoginComponent,
    DashboardComponent,
    VendordashboardComponent,
    CustomerInfoComponent,
    CustomerOrderHistoryComponent,
    VendororderhistoryComponent,
    VendorinfoComponent,
    PlaceOrderComponent,
    VendorloginComponent,
    VendorpendingorderComponent,
    WalletComponent,
    CustomerCancelOrderComponent,
    VendoracceptorrejectComponent,
    IndexComponent,
    VendorComponent,
    LogOutComponent,
    CustomerPendingComponent,
    CustomFilterPipe,
    AboutComponent,
    QrCodeComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    QRCodeModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
