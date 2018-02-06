import React from 'react';

export default class App extends React.Component {
  render() {
      console.log(this)
    return (
     <div>
        <ul>
            {this.props.bookings.map((name, index) => {
                return <li key={ index }>{name.id}</li>;
            })}
        </ul>
     </div>);
  }
}