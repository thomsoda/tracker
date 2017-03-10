const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;

class AveragesTab extends React.Component {
    render() {
        var isRanked = false;
        var playlist = null;

        var params = (isRanked == null ? "" : "isRanked=" + isRanked) + (playlist == null ? "" : "&playlist=" + playlist);

        return (
            <div>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/goal-averages?" + params} columnHeader="GOALS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/score-averages?" + params} columnHeader="SCORE"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/assist-averages?" + params} columnHeader="SAVES"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/save-averages?" + params} columnHeader="ASSISTS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/shot-averages?" + params} columnHeader="SHOTS"/>
            </div>
        );
    }
}

class Averages extends React.Component {
    constructor(props) {
        super(props);
        this.state = {averages: []};
    }

    componentDidMount() {
        client({method: 'GET', path: this.props.path}).done(response => {
            this.setState({averages: response.entity.averages});
        });
    }

    render() {
        var averages = this.state.averages.map(average =>
            <Average key={average.playerId} playerId={average.playerId} games={average.games} total={average.total} average={average.average}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="leftcell"></div>
                    <div className="cell average">PLAYED</div>
                    <div className="cell average">{this.props.columnHeader}</div>
                    <div className="cell average">{this.props.columnHeader} PER GAME</div>
                </div>
                {averages}
            </div>
        )
    }
}

class Average extends React.Component {
    render() {
        return (
            <div className="row">
                <div className="leftcell">{this.props.playerId}</div>
                <div className="cell average">{this.props.games}</div>
                <div className="cell average">{this.props.total}</div>
                <div className="cell average">{this.props.average.toFixed(2)}</div>
            </div>
        )
    }
}

export {AveragesTab};