const client = require('./client');
const React = require('react');
const Link = require('react-router').Link;

class PlayerList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {players: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/rocketleague/tracked-players/all'}).done(response => {
            this.setState({players: response.entity.playerSummaries});
        });
    }

    render() {
        var players = this.state.players.map(player =>
            <Player key={player.idPlayer} player={player}/>
        );
        return (

            <div className="wrapper">
                <div className="playername">Tracked Players</div>
                <div className="table">
                    <div className="row header">
                        <div className="cell"></div>
                        <div className="cell" title="Games Played">PLAYED</div>
                        <div className="cell" title="Win Percentage">WIN %</div>
                        <div className="cell" title="Total Goals">GOALS</div>
                        <div className="cell" title="Total Assists">ASSISTS</div>
                        <div className="cell" title="Total Saves">SAVES</div>
                        <div className="cell" title="Goals per Game">AVG GOALS</div>
                        <div className="cell" title="Assists per Game">AVG ASSISTS</div>
                        <div className="cell" title="Saves per Game">AVG SAVES</div>
                    </div>
                    {players}
                </div>
            </div>
        )
    }
}

class Player extends React.Component {
    render() {
        return (
            <Link to={"rocketleague/" + this.props.player.idPlayer} className="row clickable">
                <div className="leftcell">{this.props.player.idPlayer}</div>
                <div className="cell">{this.props.player.gamesPlayed}</div>
                <div className="cell">{this.props.player.winPercentage.toFixed(2)}</div>
                <div className="cell">{this.props.player.goals}</div>
                <div className="cell">{this.props.player.assists}</div>
                <div className="cell">{this.props.player.saves}</div>
                <div className="cell">{this.props.player.averageGoals.toFixed(2)}</div>
                <div className="cell">{this.props.player.averageAssists.toFixed(2)}</div>
                <div className="cell">{this.props.player.averageSaves.toFixed(2)}</div>
            </Link>
        )
    }
}


export {PlayerList};