package gitpumta.gitpumta.timer.service;

import gitpumta.gitpumta.timer.bean.MemberTimersBean;
import gitpumta.gitpumta.timer.bean.StartTimerBean;
import gitpumta.gitpumta.timer.bean.StopTimerBean;
import gitpumta.gitpumta.timer.domain.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TimerService {
    private final StartTimerBean startTimerBean;
    private final StopTimerBean stopTimerBean;
    private final MemberTimersBean memberTimersBean;

    public TimerService(StartTimerBean startTimerBean, StopTimerBean stopTimerBean,
                        MemberTimersBean memberTimersBean) {
        this.startTimerBean = startTimerBean;
        this.stopTimerBean = stopTimerBean;
        this.memberTimersBean = memberTimersBean;
    }

    public LocalDateTime startTimer(TimerRequestDTO timerRequestDTO) {
        UUID accountId = timerRequestDTO.getAccountId();

        startTimerBean.exec(accountId, timerRequestDTO);
        return timerRequestDTO.getSend_at();
    }

    public LocalDateTime stopTimer(TimerRequestDTO timerRequestDTO) {
        UUID accountId = timerRequestDTO.getAccountId();

        stopTimerBean.exec(accountId, timerRequestDTO);
        return timerRequestDTO.getSend_at();
    }

    public GetMemberTimersResponseDTO getMemberTimers(UUID accountId, UUID groupId) {
        GetMemberTimersResponseDTO memberTimersResponseDTO = new GetMemberTimersResponseDTO();

        memberTimersResponseDTO.setMyMonitoringGroup(memberTimersBean.getMyMonitoringGroup(groupId));
        memberTimersResponseDTO.setMyMonitoringGroupDescription(
                memberTimersBean.getMonitoringGroupDescription(groupId));
        memberTimersResponseDTO.setMyRank(memberTimersBean.getMyRank(accountId, groupId));
        memberTimersResponseDTO.setMyName(memberTimersBean.getMyName(accountId));
        memberTimersResponseDTO.setDurationLeaders(memberTimersBean.getDurationLeaders(groupId));
        memberTimersResponseDTO.setCommitLeaders(memberTimersBean.getCommitLeaders(groupId));

        return memberTimersResponseDTO;
    }
}
