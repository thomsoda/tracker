const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;

class ContributionsTab extends React.Component {
    render() {

        var params = (this.props.gameType === "ALL" ? "" : "isRanked=" + (this.props.gameType === "COMPETITIVE")) + (this.props.playlist === "ALL" ? "" : "&playlist=" + this.props.playlist);

        return (
            <div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/score-contributions?" + params} columnHeader="SCORE"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/goal-contributions?" + params} columnHeader="GOALS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/assist-contributions?" + params} columnHeader="ASSISTS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/save-contributions?" + params} columnHeader="SAVES"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/shot-contributions?" + params} columnHeader="SHOTS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/hattrick-contributions?" + params} columnHeader="HAT TRICKS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/playmaker-contributions?" + params} columnHeader="PLAYMAKERS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/saviour-contributions?" + params} columnHeader="SAVIOURS"/>
                <div className="gap"></div>
                <Contributions idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/tracked-players/mvp-contributions?" + params} columnHeader="MVPS"/>
            </div>
        );
    }
}

class Contributions extends React.Component {
    constructor(props) {
        super(props);
        this.state = {contributions: []};
    }

    componentDidMount() {
        this.loadContributions(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadContributions(nextProps.path);
    }

    loadContributions(path) {
        client({method: 'GET', path: path}).done(response => {
            this.setState({contributions: response.entity.contributions});
        });
    }

    render() {
        var ranking = 1;
        var contributions = this.state.contributions.map(contribution =>
            <Contribution key={contribution.playerId} contribution={contribution} ranking={ranking++}
                          selected={contribution.playerId === this.props.idSelectedPlayer? true : false}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="leftcell"></div>
                    <div className="cell average">{this.props.columnHeader}</div>
                    <div className="cell average">{"TEAM " + this.props.columnHeader}</div>
                    <div className="cell average">CONTRIBUTION</div>
                </div>
                {contributions}
            </div>
        )
    }
}

class Contribution extends React.Component {
    render() {
        return (
            <div className={this.props.selected? "row highlighted" : "row"}>
                <div className="leftcell">{this.props.ranking}&nbsp;&nbsp;&nbsp;&nbsp;{this.props.contribution.playerId}</div>
                <div className="cell average">{this.props.contribution.total}</div>
                <div className="cell average">{this.props.contribution.teamTotal}</div>
                <div className="cell average">{this.props.contribution.contribution.toFixed(2) + "%"}</div>
            </div>
        )
    }
}

export {ContributionsTab};