const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;
const moment = require('moment');

class MiscTab extends React.Component {
    render() {

        var params = (this.props.gameType === "ALL" ? "" : "isRanked=" + (this.props.gameType === "COMPETITIVE")) + (this.props.playlist === "ALL" ? "" : "&playlist=" + this.props.playlist);

        return (
            <div>
                <StreakTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/streak/longest-goal?" + params} columnHeader="SCORING STREAK"/>
                <div className="gap"></div>
                <StreakTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/streak/longest-drought?" + params} columnHeader="GOAL DROUGHT"/>
                <div className="gap"></div>
                <StreakTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/streak/longest-winning?" + params} columnHeader="WINNING STREAK"/>
                <div className="gap"></div>
                <StreakTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/streak/longest-losing?" + params} columnHeader="LOSING STREAK"/>
                <div className="gap"></div>
                <MostTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/most-x-in-a-game/goals?" + params} columnHeader="GOALS"/>
                <div className="gap"></div>
                <MostTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/most-x-in-a-game/assists?" + params} columnHeader="ASSISTS"/>
                <div className="gap"></div>
                <MostTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/most-x-in-a-game/saves?" + params} columnHeader="SAVES"/>
                <div className="gap"></div>
                <MostTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/most-x-in-a-game/shots?" + params} columnHeader="SHOTS"/>
                <div className="gap"></div>
                <MostTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/most-x-in-a-game/score?" + params} columnHeader="POINTS"/>
            </div>
        );
    }
}


class StreakTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {streaks: []};
    }

    componentDidMount() {
        this.loadStreaks(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadStreaks(nextProps.path);
    }

    loadStreaks(path) {
        client({method: 'GET', path: path}).done(response => {
            this.setState({streaks: response.entity.playerStreaks});
        });
    }

    render() {
        var ranking = 1;
        var streaks = this.state.streaks.map(streak =>
            <Streak key={streak.playerId} streak={streak} ranking={ranking++} selected={streak.playerId === this.props.idSelectedPlayer? true : false}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="leftcell"></div>
                    <div className="cell wide">LONGEST {this.props.columnHeader} (GAMES)</div>
                    <div className="cell average">DATE STARTED</div>
                    <div className="cell average">DATE ENDED</div>
                </div>
                {streaks}
            </div>
        )
    }
}

class Streak extends React.Component {
    render() {
        return (
            <div className={this.props.selected? "row highlighted" : "row"}>
                <div className="leftcell">{this.props.ranking}&nbsp;&nbsp;&nbsp;&nbsp;{this.props.streak.playerId}</div>
                <div className="cell wide">{this.props.streak.numberOfGames}</div>
                <div className="cell average">{this.props.streak.dtStarted == null ? "N/A" : moment(this.props.streak.dtStarted).format('DD-MMM-YYYY HH:mm')}</div>
                <div className="cell average">{this.props.streak.dtEnded == null ? "N/A" : moment(this.props.streak.dtEnded).format('DD-MMM-YYYY HH:mm')}</div>
            </div>
        )
    }
}

class MostTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {mosts: []};
    }

    componentDidMount() {
        this.loadMosts(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadMosts(nextProps.path);
    }

    loadMosts(path) {
        client({method: 'GET', path: path}).done(response => {
            this.setState({mosts: response.entity.playerMosts});
        });
    }

    render() {
        var ranking = 1;
        var mosts = this.state.mosts.map(most =>
            <Most key={most.playerId} most={most} ranking={ranking++} selected={most.playerId === this.props.idSelectedPlayer? true : false}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="leftcell"></div>
                    <div className="cell wide">MOST {this.props.columnHeader} IN A GAME</div>
                    <div className="cell average">DATE</div>
                </div>
                {mosts}
            </div>
        )
    }
}

class Most extends React.Component {
    render() {
        return (
            <Link to={"/rocketleague/" + this.props.most.playerId + "/game-history/game/" + this.props.most.gameId}
                  className={this.props.selected? "row highlighted clickable" : "row clickable"}>
                <div className="leftcell">{this.props.ranking}&nbsp;&nbsp;&nbsp;&nbsp;{this.props.most.playerId}</div>
                <div className="cell wide">{this.props.most.total}</div>
                <div className="cell average">{this.props.most.dtPlayed == null ? "N/A" : moment(this.props.most.dtPlayed).format('DD-MMM-YYYY HH:mm')}</div>
            </Link>
        )
    }
}

export {MiscTab};