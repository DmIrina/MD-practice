import React from 'react';

class DraftInput extends React.Component {
    // [txtVal, setValue] count - txtVal, setValue - функція, у параметри якої передаємо нове значення для state
    // useState у класах використовувати не можна (класи - застарілий підхід)
    // const [txtVal, setValue] = useState("event.target.value - значення інпуту, на якому спрацював event")

    constructor(props) {
        super(props);
        this.state = {
            txtVal: "event.target.value - значення інпуту, на якому спрацював event"
        }
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({txtVal: event.target.value});
    }

    render(){
        return (
            <div>
                <h3>{this.state.txtVal}</h3>
                {/*<input type="text" value={this.state.txtVal} onChange={event => this.setState({txtVal: event.target.value)}/>*/}
                <input type="text" value={this.state.txtVal} onChange={this.handleChange}/>

            </div>
        )
    }
}

export default DraftInput;