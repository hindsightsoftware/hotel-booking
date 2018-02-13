import React from 'react';
import request from 'superagent';
import Booking from './Booking.jsx';
import Nav from './Nav.jsx';
import Footer from './Footer.jsx';
import Header from './Header.jsx';
import Form from './Form.jsx';

export default class App extends React.Component {
  constructor() {
    super();
    this.state = { bookings : [] };
  }

  componentDidMount() {
    if (process.env.NODE_ENV === 'test') return // https://github.com/facebookincubator/create-react-app/issues/3482 - Allegedly will be fixed soon

    request
        .get('/booking')
        .then((res, err) => {
          const bookings = res.body;
          this.setState({bookings});
        }).catch(e => {
            console.log(e);
        })
  }

  render() {
    return (
     <div>
      <div id="wrap">
          <Nav />
          <div className="container">
              <Header />
              {this.state.bookings.map((name, index) => {
                  return <div key={name.id}><Booking id={name.id} /></div>
              })}
              <Form />
          </div>
      </div>
      <Footer />
     </div>);
  }
}