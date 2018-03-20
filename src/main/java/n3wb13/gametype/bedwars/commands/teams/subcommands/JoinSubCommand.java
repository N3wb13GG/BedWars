package n3wb13.gametype.bedwars.commands.teams.subcommands;

import n3wb13.gametype.bedwars.commands.teams.TeamCommand;
import n3wb13.gametype.bedwars.managers.players.PlayerData;
import n3wb13.gametype.bedwars.managers.teams.ETeamJoinDetails;
import n3wb13.gametype.bedwars.managers.teams.ETeamType;
import n3wb13.gametype.bedwars.managers.teams.MyTeam;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final public class JoinSubCommand extends TeamCommand {

    public JoinSubCommand() {
        this.setName("join");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (args.length >= 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                PlayerData playerData = playerManager.getPlayerData(player);

                ETeamJoinDetails details = teamManager.setTeam(playerData, args[1]);
                if (details == ETeamJoinDetails.TRUE)
                    LogUtil.log(teamManager.getTeams().get(args[1]).getDisplayName() + "に参加したよ", sender);
                else if (details == ETeamJoinDetails.GAME_PROGRES)
                    LogUtil.log("ゲームが進行中だよ", sender);
                else if (details == ETeamJoinDetails.PLAYER_MANY)
                    LogUtil.log("他のチームより人数が多いからは入れないよ", sender);
                else if (details == ETeamJoinDetails.PLAYER_MAX)
                    LogUtil.log("チーム人数が満員だよ", sender);
                else if (details == ETeamJoinDetails.NOT_EXIT)
                    LogUtil.log("存在しないチームだよ", sender);
            }
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return removeNone(teamManager.getTeams());
    }

    private ArrayList<String> removeNone(Map<String, MyTeam> teamType) {
        Map<String, MyTeam> teams = teamType;
        teams.remove(ETeamType.NONE.getTeamName());

        return new ArrayList<>(teams.keySet());
    }
}
