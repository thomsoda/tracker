const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;
const moment = require('moment');

class FormTab extends React.Component {
    render() {

        var params = (this.props.gameType === "ALL" ? "" : "isRanked=" + (this.props.gameType === "COMPETITIVE")) + (this.props.playlist === "ALL" ? "" : "&playlist=" + this.props.playlist);

        return (
            <div>
                <TeamFormTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/team-form?" + params} />
                <div className="gap"></div>
                <AggregatedFormTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/aggregated-form?" + params} />
                <div className="gap"></div>
                <FormTable idSelectedPlayer={this.props.params.idSelectedPlayer} path={"/rocketleague/form?" + params} />
            </div>
        );
    }

}

class AggregatedFormTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {aggregatedForms: []};
    }

    componentDidMount() {
        this.loadAggregatedForms(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadAggregatedForms(nextProps.path);
    }

    loadAggregatedForms(path) {
        client({method: 'GET', path: path + "&playerId=" + this.props.idSelectedPlayer}).done(response => {
            this.setState({aggregatedForms: response.entity.aggregateGameStats});
        });
    }

    render() {
        var aggregatedForms = this.state.aggregatedForms.map(aggregatedForm =>
            <AggregatedForm key={aggregatedForm.idPlayer} aggregatedForm={aggregatedForm} selected={aggregatedForm.idPlayer === this.props.idSelectedPlayer? true : false}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="cell"></div>
                    <div className="cell">PLAYED</div>
                    <div className="cell">SCORE</div>
                    <div className="cell">GOALS</div>
                    <div className="cell">ASSISTS</div>
                    <div className="cell">SAVES</div>
                    <div className="cell">SHOTS</div>
                    <div className="cell">TEAM MVPS</div>
                    <div className="cell">MVPS</div>
                </div>
                {aggregatedForms}
            </div>
        )
    }
}

class AggregatedForm extends React.Component {
    render() {
        return (
            <div className={this.props.selected? "row highlighted" : "row"}>
                <div className="leftcell">{this.props.aggregatedForm.idPlayer}</div>
                <div className="cell">{this.props.aggregatedForm.games}</div>
                <div className="cell">{this.props.aggregatedForm.score}</div>
                <div className="cell">{this.props.aggregatedForm.goals}</div>
                <div className="cell">{this.props.aggregatedForm.assists}</div>
                <div className="cell">{this.props.aggregatedForm.saves}</div>
                <div className="cell">{this.props.aggregatedForm.shots}</div>
                <div className="cell">{this.props.aggregatedForm.teamMvps}</div>
                <div className="cell">{this.props.aggregatedForm.mvps}</div>
            </div>
        )
    }
}

class FormTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {forms: []};
    }

    componentDidMount() {
        this.loadForm(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadForm(nextProps.path);
    }

    loadForm(path) {
        client({method: 'GET', path: path + "&playerId=" + this.props.idSelectedPlayer}).done(response => {
            this.setState({forms: response.entity.gameStats});
        });
    }

    render() {
        var performances = this.state.forms.map(performance =>
            <Performance key={performance.dtPlayed} performance={performance}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="leftcell">DATE</div>
                    <div className="cell"></div>
                    <div className="cell">SCORE</div>
                    <div className="cell">GOALS</div>
                    <div className="cell">ASSISTS</div>
                    <div className="cell">SAVES</div>
                    <div className="cell">SHOTS</div>
                </div>
                {performances}
            </div>
        )
    }
}

class Performance extends React.Component {
    render() {
        return (
            <Link to={"/rocketleague/" + this.props.performance.idPlayer + "/game-history/game/" + this.props.performance.gameId}
                  className="row clickable">
                <div className="leftcell">{moment(this.props.performance.dtPlayed).format('DD-MMM-YYYY HH:mm')}</div>
                <div className="cell">{this.props.performance.wasMvp ? 'MVP' : (this.props.performance.wasTeamMvp ? 'TEAM MVP' : '')}</div>
                <div className="cell">{this.props.performance.score}</div>
                <div className="cell">{this.props.performance.goals}</div>
                <div className="cell">{this.props.performance.assists}</div>
                <div className="cell">{this.props.performance.saves}</div>
                <div className="cell">{this.props.performance.shots}</div>
            </Link>
        )
    }
}

class TeamFormTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {teamForm: []};
    }

    componentDidMount() {
        this.loadTeamForm(this.props.path);
    }

    componentWillReceiveProps(nextProps) {
        this.loadTeamForm(nextProps.path);
    }

    loadTeamForm(path) {
        client({method: 'GET', path: path + "&playerId=" + this.props.idSelectedPlayer}).done(response => {
            this.setState({teamForm: response.entity.teamStats});
        });
    }

    render() {
        var teamForm = this.state.teamForm.map(form =>
            <TeamForm key={form.playerId} form={form}/>
        );

        return (
            <div className="table">
                <div className="row header">
                    <div className="cell">PLAYED</div>
                    <div className="cell">WON</div>
                    <div className="cell">LOST</div>
                    <div className="cell">GOALS FOR</div>
                    <div className="cell">GOALS AGAINST</div>
                    <div className="cell">GOAL DIFF</div>
                    <div className="cell">CLEAN SHEETS</div>
                </div>
                {teamForm}
            </div>
        )
    }
}

class TeamForm extends React.Component {
    render() {
        return (
            <div className="row">
                <div className="cell">{this.props.form.games}</div>
                <div className="cell">{this.props.form.won}</div>
                <div className="cell">{this.props.form.lost}</div>
                <div className="cell">{this.props.form.goalsFor}</div>
                <div className="cell">{this.props.form.goalsAgainst}</div>
                <div className="cell">{(this.props.form.goalDifference > 0? "+" : "") + this.props.form.goalDifference}</div>
                <div className="cell">{this.props.form.cleanSheets}</div>
            </div>
        )
    }
}

export {FormTab};