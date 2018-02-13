import React from 'react';

export default class Header extends React.Component {
  render() {
    return (
        <div className="row">
            <div className="col-md-1"><p className="lead">Firstname</p></div>
            <div className="col-md-2"><p className="lead">Lastname</p></div>
            <div className="col-md-1"><p className="lead">Price</p></div>
            <div className="col-md-1"><p className="lead">Deposit</p></div>
            <div className="col-md-2"><p className="lead">Additional</p></div>
            <div className="col-md-2"><p className="lead">Checkin</p></div>
            <div className="col-md-2"><p className="lead">Checkout</p></div>
            <div className="col-md-1"></div>      
        </div>);
  }
}