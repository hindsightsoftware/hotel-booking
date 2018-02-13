import React from 'react';

export default class Form extends React.Component {

    handleClick(){
        console.log("This was clicked on");
    }

    render() {
        return (
            <div className="row">
                <div className="col-md-1"><input type="text" className="form-control input-sm" placeholder="Firstname" /></div>
                <div className="col-md-2"><input type="text" className="form-control input-sm" placeholder="Lastname" /></div>
                <div className="col-md-1"><input type="text" className="form-control input-sm" placeholder="Price" /></div>
                <div className="col-md-1"><input type="text" className="form-control input-sm" placeholder="Deposit?" /></div>
                <div className="col-md-2"><input type="text" className="form-control input-sm" placeholder="Additional" /></div>
                <div className="col-md-2"><input type="text" className="form-control input-sm" placeholder="Checkin" /></div>
                <div className="col-md-2"><input type="text" className="form-control input-sm" placeholder="Checkout" /></div>
                <div className="col-md-1"><button onClick={this.handleClick} className="btn btn-sm">Add</button></div>
            </div>
        )
    }

}