const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;

class AveragesTab extends React.Component {
    render() {

        var params = (this.props.gameType === "ALL" ? "" : "isRanked=" + (this.props.gameType === "COMPETITIVE")) + (this.props.playlist === "ALL" ? "" : "&playlist=" + this.props.playlist);

        return (
            <div>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/score-averages?" + params} columnHeader="SCORE"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/goal-averages?" + params} columnHeader="GOALS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/assist-averages?" + params} columnHeader="SAVES"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/save-averages?" + params} columnHeader="ASSISTS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/shot-averages?" + params} columnHeader="SHOTS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/hattrick-averages?" + params} columnHeader="HAT TRICKS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/playmaker-averages?" + params} columnHeader="PLAYMAKERS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/saviour-averages?" + params} columnHeader="SAVIOURS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/mvp-averages?" + params} columnHeader="MVPS"/>
                <Averages idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/team-mvp-averages?" + params} columnHeader="TEAM MVPS"/>
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
        this.loadAverages(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadAverages(nextProps.path);
    }

    loadAverages(path) {
        client({method: 'GET', path: path}).done(response => {
            this.setState({averages: response.entity.averages});
        });
    }

    render() {
        var ranking = 1;
        var averages = this.state.averages.map(average =>
            <Average key={average.playerId} playerId={average.playerId} games={average.games} total={average.total}
                     average={average.average} ranking={ranking++} selected={average.playerId === this.props.idSelectedPlayer? true : false}/>
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
            <div className={this.props.selected? "row highlighted" : "row"}>
                <div className="leftcell">{this.props.ranking}&nbsp;&nbsp;&nbsp;&nbsp;{this.props.playerId}</div>
                <div className="cell average">{this.props.games}</div>
                <div className="cell average">{this.props.total}</div>
                <div className="cell average">{this.props.average.toFixed(2)}</div>
            </div>
        )
    }
}

export {AveragesTab};