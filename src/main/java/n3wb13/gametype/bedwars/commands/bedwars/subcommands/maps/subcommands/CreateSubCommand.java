package n3wb13.gametype.bedwars.commands.bedwars.subcommands.maps.subcommands;

import n3wb13.gametype.bedwars.commands.bedwars.subcommands.maps.MapSubCommand;
import n3wb13.gametype.bedwars.managers.maps.EMapCreateDetails;
import n3wb13.gametype.bedwars.utils.LogUtil;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CreateSubCommand extends MapSubCommand {

    public CreateSubCommand() {
        this.setName("create");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (args.length >= 3) {
            EMapCreateDetails details = mapManager.createMap(args[2]);
            if (details == EMapCreateDetails.TRUE)
                LogUtil.log(args[2] + "を作成したよ", sender);
            else if (details == EMapCreateDetails.MAP_FILE_NOT_EXIST)
                LogUtil.log(args[2] + "を作成したよ、でもワールドデータが無いよ!", sender);
            else if (details == EMapCreateDetails.EXIST)
                LogUtil.log(args[2] + "は既に存在するよ!", sender);
        }

        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return new ArrayList<>();
    }
}
