package com.joaomgcd.taskerm.settings;

import java.util.ArrayList;
import java.util.Collection;
import net.danlew.android.joda.R;
import net.dinglisch.android.taskerm.bg;
import net.dinglisch.android.taskerm.gr;

/* JADX INFO: loaded from: classes.dex */
public final class ac {
    public static final int[] a(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(Integer.valueOf(R.string.settings_divider_lock), Integer.valueOf(R.string.settings_divider_localization), Integer.valueOf(R.string.settings_divider_ui_profile_list_view), Integer.valueOf(R.string.settings_divider_ui_general), Integer.valueOf(R.string.settings_divider_ui_general), Integer.valueOf(R.string.settings_divider_screen_off_or_on), Integer.valueOf(R.string.settings_divider_screen_off), Integer.valueOf(R.string.settings_divider_screen_on), Integer.valueOf(R.string.settings_divider_gestures), Integer.valueOf(R.string.settings_divider_debug), Integer.valueOf(R.string.app_factory_name), Integer.valueOf(R.string.settings_divider_gestures), Integer.valueOf(R.string.settings_proximity_sensor), Integer.valueOf(R.string.settings_light_sensor), Integer.valueOf(R.string.settings_magnet_sensor), Integer.valueOf(R.string.settings_pressure_sensor), Integer.valueOf(R.string.settings_step_sensor), Integer.valueOf(R.string.settings_temperature_sensor), Integer.valueOf(R.string.settings_humidity_sensor), Integer.valueOf(R.string.settings_profile_textsize_label), Integer.valueOf(R.string.settings_show_enable_label), Integer.valueOf(R.string.settings_ask_profile_label), Integer.valueOf(R.string.settings_confirm_context_and_task_deletes_label), Integer.valueOf(R.string.settings_confirm_profile_deletes_label), Integer.valueOf(R.string.settings_auto_collapse_label), Integer.valueOf(R.string.settings_animations_label), Integer.valueOf(R.string.settings_haptic_label), Integer.valueOf(R.string.settings_tips_label), Integer.valueOf(R.string.settings_orientation_label), Integer.valueOf(R.string.settings_orientation_state_sensitivity_label), Integer.valueOf(R.string.settings_lock_code_label), Integer.valueOf(R.string.settings_lock_all_label), Integer.valueOf(R.string.settings_lang_label), Integer.valueOf(R.string.settings_monitor_fore_label), Integer.valueOf(R.string.settings_monitor_trans_label), Integer.valueOf(R.string.settings_custom_pulldown_label), Integer.valueOf(R.string.settings_launch_check_time_label), Integer.valueOf(R.string.settings_custom_not_task_label), Integer.valueOf(R.string.settings_qst_task_label), Integer.valueOf(R.string.settings_appshortcuts_label), Integer.valueOf(R.string.settings_wifi_check_time_label), Integer.valueOf(R.string.settings_bt_check_time_label), Integer.valueOf(R.string.settings_gps_check_time_label), Integer.valueOf(R.string.settings_loc_check_time_label), Integer.valueOf(R.string.settings_gps_check_timeout_label), Integer.valueOf(R.string.settings_off_check_time_label), Integer.valueOf(R.string.settings_off_check_timeout_label), Integer.valueOf(R.string.settings_wifi_check_timeout_label), Integer.valueOf(R.string.settings_bt_check_timeout_label), Integer.valueOf(R.string.settings_gesture_match_sample_rate_initial_label), Integer.valueOf(R.string.settings_gesture_match_radius_label), Integer.valueOf(R.string.settings_gesture_match_reset_time_label), Integer.valueOf(R.string.settings_iterations_label), Integer.valueOf(R.string.settings_algorithm_label), Integer.valueOf(R.string.settings_focus_time_label), Integer.valueOf(R.string.settings_workaround_label), Integer.valueOf(R.string.settings_wakeup_label), Integer.valueOf(R.string.settings_toast_enable_label), Integer.valueOf(R.string.settings_max_tasks_label), Integer.valueOf(R.string.settings_update_check_label), Integer.valueOf(R.string.settings_allow_ext_label), Integer.valueOf(R.string.settings_docs_online_label), Integer.valueOf(R.string.settings_extra_logging_label), Integer.valueOf(R.string.settings_debugsd_label), Integer.valueOf(R.string.settings_autobackup_label), Integer.valueOf(R.string.settings_logging_enable_label), Integer.valueOf(R.string.settings_hide_status_bar_label), Integer.valueOf(R.string.settings_drag_mode_label), Integer.valueOf(R.string.settings_appcheck_method_label), Integer.valueOf(R.string.word_theme), Integer.valueOf(R.string.settings_factory_external_cache_label), Integer.valueOf(R.string.settings_save_memory_label), Integer.valueOf(R.string.settings_new_cell_api_label), Integer.valueOf(R.string.ml_backup_type_google_drive), Integer.valueOf(R.string.settings_motion_detection_label), Integer.valueOf(R.string.settings_clock_alarms_label), Integer.valueOf(R.string.pl_hardware_acceleration), Integer.valueOf(R.string.settings_lock_icon_colour_label), Integer.valueOf(R.string.settings_monitor_show_icon_label));
        abVar.a(arrayListD, b.f8573a);
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    static final class b extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.c<?, ?>, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f8573a = new b();

        b() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            return Integer.valueOf(a(cVar));
        }

        public final int a(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            c.f.b.k.b(cVar, "receiver$0");
            return cVar.f();
        }
    }

    public static final int[] b(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(Integer.valueOf(R.id.settings_divider_lock), Integer.valueOf(R.id.settings_divider_localization), Integer.valueOf(R.id.settings_divider_ui_profile_list_view), Integer.valueOf(R.id.settings_divider_ui_general), Integer.valueOf(R.id.settings_divider_monitor_general), Integer.valueOf(R.id.settings_divider_screen_off_or_on), Integer.valueOf(R.id.settings_divider_screen_off), Integer.valueOf(R.id.settings_divider_screen_on), Integer.valueOf(R.id.settings_divider_gestures), Integer.valueOf(R.id.settings_divider_debug), Integer.valueOf(R.id.settings_divider_factory), Integer.valueOf(R.id.settings_accel_when_off_label), Integer.valueOf(R.id.settings_prox_when_off_label), Integer.valueOf(R.id.settings_light_when_off_label), Integer.valueOf(R.id.settings_magnet_when_off_label), Integer.valueOf(R.id.settings_pressure_when_off_label), Integer.valueOf(R.id.settings_step_when_off_label), Integer.valueOf(R.id.settings_temp_when_off_label), Integer.valueOf(R.id.settings_humidity_when_off_label), Integer.valueOf(R.id.settings_profile_textsize_label), Integer.valueOf(R.id.settings_show_enable_label), Integer.valueOf(R.id.settings_ask_profile_label), Integer.valueOf(R.id.settings_confirm_context_and_task_deletes_label), Integer.valueOf(R.id.settings_confirm_profile_deletes_label), Integer.valueOf(R.id.settings_auto_collapse_label), Integer.valueOf(R.id.settings_animations_label), Integer.valueOf(R.id.settings_haptic_label), Integer.valueOf(R.id.settings_tips_label), Integer.valueOf(R.id.settings_orientation_label), Integer.valueOf(R.id.settings_orientation_state_sensitivity_label), Integer.valueOf(R.id.settings_lock_code_label), Integer.valueOf(R.id.settings_lock_all_label), Integer.valueOf(R.id.settings_lang_label), Integer.valueOf(R.id.settings_monitor_fore_label), Integer.valueOf(R.id.settings_monitor_trans_label), Integer.valueOf(R.id.settings_custom_pulldown_label), Integer.valueOf(R.id.settings_launch_check_time_label), Integer.valueOf(R.id.settings_custom_not_task_label), Integer.valueOf(R.id.settings_qst_task_label), Integer.valueOf(R.id.settings_appshortcut_label), Integer.valueOf(R.id.settings_wifi_check_time_label), Integer.valueOf(R.id.settings_bt_check_time_label), Integer.valueOf(R.id.settings_gps_check_time_label), Integer.valueOf(R.id.settings_loc_check_time_label), Integer.valueOf(R.id.settings_gps_check_timeout_label), Integer.valueOf(R.id.settings_off_check_time_label), Integer.valueOf(R.id.settings_off_check_timeout_label), Integer.valueOf(R.id.settings_wifi_check_timeout_label), Integer.valueOf(R.id.settings_bt_check_timeout_label), Integer.valueOf(R.id.settings_gesture_match_sample_rate_initial_label), Integer.valueOf(R.id.settings_gesture_match_radius_label), Integer.valueOf(R.id.settings_gesture_match_reset_time_label), Integer.valueOf(R.id.settings_iterations_label), Integer.valueOf(R.id.settings_algorithm_label), Integer.valueOf(R.id.settings_focus_time_label), Integer.valueOf(R.id.settings_workaround_label), Integer.valueOf(R.id.settings_wakeup_label), Integer.valueOf(R.id.settings_toast_enable_label), Integer.valueOf(R.id.settings_max_tasks_label), Integer.valueOf(R.id.settings_update_check_label), Integer.valueOf(R.id.settings_allow_ext_label), Integer.valueOf(R.id.settings_docs_online_label), Integer.valueOf(R.id.settings_extra_logging_label), Integer.valueOf(R.id.settings_debugsd_label), Integer.valueOf(R.id.settings_change_track_label), Integer.valueOf(R.id.settings_logging_enable_label), Integer.valueOf(R.id.settings_hide_status_bar_label), Integer.valueOf(R.id.settings_drag_mode_label), Integer.valueOf(R.id.settings_appcheck_method_label), Integer.valueOf(R.id.theme_label), Integer.valueOf(R.id.settings_factory_external_cache_label), Integer.valueOf(R.id.settings_save_memory_label), Integer.valueOf(R.id.settings_new_cell_api_label), Integer.valueOf(R.id.settings_google_drive_backup_label), Integer.valueOf(R.id.settings_sm_label), Integer.valueOf(R.id.settings_clock_alarms_label), Integer.valueOf(R.id.settings_ui_hw_accel_label), Integer.valueOf(R.id.settings_lock_icon_colour_label), Integer.valueOf(R.id.settings_monitor_show_icon_label));
        abVar.a(arrayListD, a.f8572a);
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    static final class a extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.c<?, ?>, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f8572a = new a();

        a() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            return Integer.valueOf(a(cVar));
        }

        public final int a(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            c.f.b.k.b(cVar, "receiver$0");
            return cVar.e();
        }
    }

    public static final int[] c(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        Integer[] numArr = new Integer[53];
        numArr[0] = Integer.valueOf(R.string.settings_divider_gestures);
        numArr[1] = Integer.valueOf(R.string.settings_proximity_sensor);
        numArr[2] = Integer.valueOf(R.string.settings_light_sensor);
        numArr[3] = Integer.valueOf(R.string.settings_magnet_sensor);
        numArr[4] = Integer.valueOf(R.string.settings_pressure_sensor);
        numArr[5] = Integer.valueOf(R.string.settings_step_sensor);
        numArr[6] = Integer.valueOf(R.string.settings_temperature_sensor);
        numArr[7] = Integer.valueOf(R.string.settings_humidity_sensor);
        numArr[8] = Integer.valueOf(R.string.settings_gesture_match_radius_label);
        numArr[9] = Integer.valueOf(R.string.settings_gesture_match_sample_rate_initial_label);
        numArr[10] = Integer.valueOf(R.string.settings_gesture_match_reset_time_label);
        numArr[11] = Integer.valueOf(gr.q() ? R.string.settings_launch_check_time_label_post_l : R.string.settings_launch_check_time_label);
        numArr[12] = Integer.valueOf(R.string.settings_custom_not_task_label);
        numArr[13] = Integer.valueOf(R.string.settings_qst_task_label);
        numArr[14] = Integer.valueOf(R.string.settings_appshortcuts_label);
        numArr[15] = Integer.valueOf(R.string.settings_lock_code_label);
        numArr[16] = Integer.valueOf(R.string.settings_iterations_label);
        numArr[17] = Integer.valueOf(R.string.settings_loc_check_time_label);
        numArr[18] = Integer.valueOf(R.string.settings_gps_check_time_label);
        numArr[19] = Integer.valueOf(R.string.settings_gps_check_timeout_label);
        numArr[20] = Integer.valueOf(R.string.settings_wifi_check_time_label);
        numArr[21] = Integer.valueOf(R.string.settings_wifi_check_timeout_label);
        numArr[22] = Integer.valueOf(R.string.settings_bt_check_time_label);
        numArr[23] = Integer.valueOf(R.string.settings_bt_check_timeout_label);
        numArr[24] = Integer.valueOf(R.string.settings_off_check_time_label);
        numArr[25] = Integer.valueOf(R.string.settings_off_check_timeout_label);
        numArr[26] = Integer.valueOf(R.string.settings_logging_enable_label);
        numArr[27] = Integer.valueOf(R.string.settings_extra_logging_label);
        numArr[28] = Integer.valueOf(R.string.settings_debugsd_label);
        numArr[29] = Integer.valueOf(R.string.settings_monitor_fore_label);
        numArr[30] = Integer.valueOf(R.string.settings_custom_pulldown_label);
        numArr[31] = Integer.valueOf(R.string.settings_monitor_trans_label);
        numArr[32] = Integer.valueOf(R.string.settings_workaround_label);
        numArr[33] = Integer.valueOf(R.string.settings_wakeup_label);
        numArr[34] = Integer.valueOf(R.string.settings_toast_enable_label);
        numArr[35] = Integer.valueOf(R.string.settings_allow_ext_label);
        numArr[36] = Integer.valueOf(R.string.settings_auto_collapse_label);
        numArr[37] = Integer.valueOf(R.string.settings_algorithm_label);
        numArr[38] = Integer.valueOf(R.string.settings_update_check_label);
        numArr[39] = Integer.valueOf(R.string.settings_max_tasks_label);
        numArr[40] = Integer.valueOf(R.string.settings_focus_time_label);
        numArr[41] = Integer.valueOf(R.string.settings_lock_all_label);
        numArr[42] = Integer.valueOf(R.string.settings_autobackup_label);
        numArr[43] = Integer.valueOf(R.string.settings_orientation_state_sensitivity_label);
        numArr[44] = Integer.valueOf(R.string.settings_factory_external_cache_label);
        numArr[45] = Integer.valueOf(R.string.settings_save_memory_label);
        numArr[46] = Integer.valueOf(R.string.settings_new_cell_api_label);
        numArr[47] = Integer.valueOf(R.string.ml_backup_type_google_drive);
        numArr[48] = Integer.valueOf(R.string.settings_motion_detection_label);
        numArr[49] = Integer.valueOf(R.string.settings_clock_alarms_label);
        numArr[50] = Integer.valueOf(R.string.pl_hardware_acceleration);
        numArr[51] = Integer.valueOf(R.string.settings_lock_icon_colour_label);
        numArr[52] = Integer.valueOf(R.string.settings_monitor_show_icon_label);
        ArrayList arrayListD = c.a.j.d(numArr);
        abVar.a(arrayListD, e.f8576a);
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    static final class e extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.c<?, ?>, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f8576a = new e();

        e() {
            super(1);
        }

        @Override // c.f.a.b
        public /* synthetic */ Integer invoke(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            return Integer.valueOf(a(cVar));
        }

        public final int a(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            c.f.b.k.b(cVar, "receiver$0");
            return cVar.f();
        }
    }

    public static final int[] d(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(Integer.valueOf(R.id.button_accel_when_off_help), Integer.valueOf(R.id.button_prox_when_off_help), Integer.valueOf(R.id.button_light_when_off_help), Integer.valueOf(R.id.button_magnet_when_off_help), Integer.valueOf(R.id.button_pressure_when_off_help), Integer.valueOf(R.id.button_step_when_off_help), Integer.valueOf(R.id.button_temp_when_off_help), Integer.valueOf(R.id.button_humidity_when_off_help), Integer.valueOf(R.id.button_gesture_match_radius_help), Integer.valueOf(R.id.button_gesture_match_sample_rate_initial_help), Integer.valueOf(R.id.button_gesture_match_reset_time_help), Integer.valueOf(R.id.button_launch_check_time_help), Integer.valueOf(R.id.button_custom_not_task_help), Integer.valueOf(R.id.button_qst_task_help), Integer.valueOf(R.id.button_appshortcuts_help), Integer.valueOf(R.id.button_lock_code_help), Integer.valueOf(R.id.button_iterations_help), Integer.valueOf(R.id.button_loc_check_time_help), Integer.valueOf(R.id.button_gps_check_time_help), Integer.valueOf(R.id.button_gps_check_timeout_help), Integer.valueOf(R.id.button_wifi_check_time_help), Integer.valueOf(R.id.button_wifi_check_timeout_help), Integer.valueOf(R.id.button_bt_check_time_help), Integer.valueOf(R.id.button_bt_check_timeout_help), Integer.valueOf(R.id.button_off_check_time_help), Integer.valueOf(R.id.button_off_check_timeout_help), Integer.valueOf(R.id.button_logging_enable_help), Integer.valueOf(R.id.button_extra_logging_help), Integer.valueOf(R.id.button_debugsd_help), Integer.valueOf(R.id.button_monitor_fore_help), Integer.valueOf(R.id.button_custom_pulldown_help), Integer.valueOf(R.id.button_monitor_trans_help), Integer.valueOf(R.id.button_workaround_help), Integer.valueOf(R.id.button_wakeup_help), Integer.valueOf(R.id.button_toast_enable_help), Integer.valueOf(R.id.button_allow_ext_help), Integer.valueOf(R.id.button_auto_collapse_help), Integer.valueOf(R.id.button_algorithm_help), Integer.valueOf(R.id.button_update_check_help), Integer.valueOf(R.id.button_max_tasks_help), Integer.valueOf(R.id.button_focus_time_help), Integer.valueOf(R.id.button_lock_all_help), Integer.valueOf(R.id.button_change_track_help), Integer.valueOf(R.id.button_ori_sensitivity_help), Integer.valueOf(R.id.button_factory_external_cache_help), Integer.valueOf(R.id.button_save_memory_help), Integer.valueOf(R.id.button_new_cell_api_help), Integer.valueOf(R.id.button_google_drive_backup_help), Integer.valueOf(R.id.button_sm_help), Integer.valueOf(R.id.button_clock_alarms_help), Integer.valueOf(R.id.button_ui_hw_accel_help), Integer.valueOf(R.id.button_lock_icon_colour_help), Integer.valueOf(R.id.button_monitor_show_icon_help));
        abVar.a(arrayListD, c.f8574a);
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    static final class c extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.c<?, ?>, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f8574a = new c();

        c() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            c.f.b.k.b(cVar, "receiver$0");
            s sVarH = cVar.h();
            if (sVarH != null) {
                return Integer.valueOf(sVarH.a());
            }
            return null;
        }
    }

    public static final int[] e(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(Integer.valueOf(R.string.settings_help_accel_when_off), Integer.valueOf(R.string.settings_help_prox_when_off), Integer.valueOf(R.string.settings_help_light_when_off), Integer.valueOf(R.string.settings_help_magnet_when_off), Integer.valueOf(R.string.settings_help_pressure_when_off), Integer.valueOf(R.string.settings_help_step_when_off), Integer.valueOf(R.string.settings_help_temperature_when_off), Integer.valueOf(R.string.settings_help_humidity_when_off), Integer.valueOf(R.string.settings_help_gesture_match_radius), Integer.valueOf(R.string.settings_help_gesture_match_sample_rate_initial), Integer.valueOf(R.string.settings_help_gesture_match_reset_time), Integer.valueOf(R.string.settings_help_launch_check_period), Integer.valueOf(R.string.settings_help_custom_not_task), Integer.valueOf(R.string.settings_help_qst_task), Integer.valueOf(R.string.settings_help_appshortcuts), Integer.valueOf(R.string.settings_help_lock_code), Integer.valueOf(R.string.settings_help_iterations), Integer.valueOf(R.string.settings_help_loc_check_period), Integer.valueOf(R.string.settings_help_gps_check_period), Integer.valueOf(R.string.settings_help_gps_check_timeout), Integer.valueOf(R.string.settings_help_wifi_check_period), Integer.valueOf(R.string.settings_help_wifi_check_timeout), Integer.valueOf(R.string.settings_help_bt_check_period), Integer.valueOf(R.string.settings_help_bt_check_timeout), Integer.valueOf(R.string.settings_help_off_check_period), Integer.valueOf(R.string.settings_help_off_check_timeout), Integer.valueOf(R.string.settings_help_logging_enable), Integer.valueOf(R.string.settings_help_extra_logging), Integer.valueOf(R.string.settings_help_debugsd), Integer.valueOf(R.string.settings_help_monitor_fore), Integer.valueOf(R.string.settings_help_custom_pulldown), Integer.valueOf(R.string.settings_help_monitor_trans), Integer.valueOf(R.string.settings_help_workaround), Integer.valueOf(R.string.settings_help_wakeup), Integer.valueOf(R.string.settings_help_toast_enable), Integer.valueOf(R.string.settings_help_allow_ext), Integer.valueOf(R.string.settings_help_auto_collapse), Integer.valueOf(R.string.settings_help_algorithm), Integer.valueOf(R.string.settings_help_update_check), Integer.valueOf(R.string.settings_help_max_tasks), Integer.valueOf(R.string.settings_help_focus_time), Integer.valueOf(R.string.settings_help_lock_all), Integer.valueOf(R.string.settings_help_autobackup), Integer.valueOf(R.string.settings_help_orientation_state_sensitivity), Integer.valueOf(R.string.settings_help_factory_external_cache), Integer.valueOf(R.string.settings_help_save_memory), Integer.valueOf(R.string.settings_help_new_cell_api), Integer.valueOf(R.string.settings_help_google_drive_backup), Integer.valueOf(R.string.settings_help_motion_detection), Integer.valueOf(R.string.settings_help_clock_alarms), Integer.valueOf(R.string.settings_help_ui_hw_accel), Integer.valueOf(R.string.settings_help_lock_icon_colour), Integer.valueOf(R.string.settings_help_monitor_show_icon));
        abVar.a(arrayListD, d.f8575a);
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    static final class d extends c.f.b.l implements c.f.a.b<com.joaomgcd.taskerm.settings.c<?, ?>, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f8575a = new d();

        d() {
            super(1);
        }

        @Override // c.f.a.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final Integer invoke(com.joaomgcd.taskerm.settings.c<?, ?> cVar) {
            c.f.b.k.b(cVar, "receiver$0");
            s sVarH = cVar.h();
            if (sVarH != null) {
                return Integer.valueOf(sVarH.b());
            }
            return null;
        }
    }

    public static final int[] f(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(Integer.valueOf(R.id.extra_logging_checkbox), Integer.valueOf(R.id.docs_online_checkbox), Integer.valueOf(R.id.debugsd_checkbox), Integer.valueOf(R.id.logging_enable_checkbox), Integer.valueOf(R.id.monitor_fore_checkbox), Integer.valueOf(R.id.workaround_checkbox), Integer.valueOf(R.id.wakeup_checkbox), Integer.valueOf(R.id.toast_enable_checkbox), Integer.valueOf(R.id.tips_checkbox), Integer.valueOf(R.id.lock_all_checkbox), Integer.valueOf(R.id.haptic_checkbox), Integer.valueOf(R.id.allow_ext_checkbox), Integer.valueOf(R.id.ask_profile_checkbox), Integer.valueOf(R.id.animations_checkbox), Integer.valueOf(R.id.show_enable_checkbox), Integer.valueOf(R.id.confirm_context_and_task_deletes_checkbox), Integer.valueOf(R.id.confirm_profile_deletes_checkbox), Integer.valueOf(R.id.factory_external_cache_checkbox), Integer.valueOf(R.id.save_memory_checkbox), Integer.valueOf(R.id.new_cell_api_checkbox), Integer.valueOf(R.id.monitor_not_actions_checkbox), Integer.valueOf(R.id.google_drive_backup_checkbox), Integer.valueOf(R.id.sm_checkbox), Integer.valueOf(R.id.appshortcut_checkbox), Integer.valueOf(R.id.ui_hw_accel_checkbox), Integer.valueOf(R.id.lock_icon_colour_checkbox), Integer.valueOf(R.id.monitor_show_icon_checkbox));
        for (com.joaomgcd.taskerm.settings.c<?, ?> cVar : abVar) {
            if (cVar instanceof f) {
                arrayListD.add(Integer.valueOf(((f) cVar).g()));
            }
        }
        return c.a.j.e((Collection<Integer>) arrayListD);
    }

    public static final boolean[] g(ab abVar) {
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d(false, false, false, false, true, false, false, true, true, false, true, false, false, false, true, true, true, false, true, false, false, false, false, true, Boolean.valueOf(bg.j.b.f11825a), true, true);
        for (com.joaomgcd.taskerm.settings.c<?, ?> cVar : abVar) {
            if (cVar instanceof f) {
                arrayListD.add(Boolean.valueOf(((f) cVar).d().booleanValue()));
            }
        }
        return c.a.j.b((Collection<Boolean>) arrayListD);
    }

    public static final String[] h(ab abVar) {
        String strC;
        c.f.b.k.b(abVar, "manual");
        ArrayList arrayListD = c.a.j.d("leEnle", "avo", "dsd", "lEnable", "mFn", "workForLoc", "wakeForLoc", "tEnable", "tpEn", "lae", "sHapt", "aExt", "apn", "anm", "pen", "lPEna", "lPdta", "fExtCache", "saveMemory", "newCellApi", "notactions", "googleDriveBackups", "sigmotion", "asactions", "hardwareAccel", "lockIconColour", "mSi");
        for (com.joaomgcd.taskerm.settings.c<?, ?> cVar : abVar) {
            if ((cVar instanceof f) && (strC = ((f) cVar).c()) != null) {
                arrayListD.add(strC);
            }
        }
        Object[] array = arrayListD.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new c.q("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
